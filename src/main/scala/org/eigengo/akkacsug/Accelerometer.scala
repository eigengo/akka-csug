package org.eigengo.akkacsug

import scodec.Codec
import scodec.bits.BitVector

import scala.annotation.tailrec

case class AccelerometerData(samplingRate: Int, values: List[AccelerometerValue])
case class AccelerometerValue(x: Int, y: Int, z: Int)

object AccelerometerData {
  import scodec.codecs._

  private type ZYX = (Int, Int, Int)
  private type CSU = (Int, Int, Unit)

  private implicit val packedAccelerometerData: Codec[ZYX] = new FixedSizeReversedCodec(40, {
    ignore(1) :~>: ("z" | int(13)) :: ("y" | int(13)) :: ("x" | int(13))
  }).as[ZYX]

  private implicit val packedGfsHeader: Codec[CSU] = new FixedSizeReversedCodec(40, {
    ("samplesPerSecond" | int8) :: ("count" | int16) :: constant(BitVector(0xfe, 0xfc))
  }).as[CSU]

  def parse(bits: BitVector): (BitVector, List[AccelerometerData]) = {
    implicit val _ = scalaz.Monoid.instance[String](_ + _, "")
    val result = for {
      (body, (samplesPerSecond, count, _)) <- Codec.decode[CSU](bits)
      (rest, zyxs) <- Codec.decodeCollect[List, ZYX](packedAccelerometerData, Some(count))(body)
      avs = zyxs.map { case (z, y, x) => AccelerometerValue(x, y, z) }
    } yield (rest, AccelerometerData(samplesPerSecond, avs))

    result.fold(_ => (bits, Nil), { case (bits2, ad) => (bits2, List(ad)) })
  }

  @tailrec
  final def parseAll(bits: BitVector, ads: List[AccelerometerData]): (BitVector, List[AccelerometerData]) = {
    parse(bits) match {
      // Parsed all we could, nothing remains
      case (BitVector.empty, ads2) => (BitVector.empty, ads ++ ads2)
      // Parsed all we could, but exactly `bits` remain => we did not get any further.
      // Repeated recursion will not solve anything.
      case (`bits`, ads2) => (bits, ads ++ ads2)
      // Still something left to parse
      case (neb, ads2) => parseAll(neb, ads ++ ads2)
    }
  }

}
