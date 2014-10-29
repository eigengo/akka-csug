package org.eigengo.akkacsug

import scodec.Codec
import scodec.bits.BitVector

import scala.annotation.tailrec

/**
 * Accelerometer data groups ``values`` at the given ``samplingRate``
 * @param samplingRate the sampling rate in Hz
 * @param values the values
 */
case class AccelerometerData(samplingRate: Int, values: List[AccelerometerValue])

/**
 * Accelerometer data
 * @param x the x
 * @param y the y
 * @param z the z
 */
case class AccelerometerValue(x: Int, y: Int, z: Int)

/**
 * Contains decoders for the stream of paced values in a stream constructed from
 *
 * {{{
 * #define GFS_HEADER_TYPE (uint16_t)0xfefc
 *
 * /**
 * * 5 B in header
 * */
 * struct __attribute__((__packed__)) gfs_header {
 *     uint16_t type;
 *     uint16_t count;
 *     uint8_t samples_per_second;
 * };
 *
 * /**
 * * Packed 5 B of the accelerometer values
 * */
 * struct __attribute__((__packed__)) gfs_packed_accel_data {
 *     int16_t x_val : 13;
 *     int16_t y_val : 13;
 *     int16_t z_val : 13;
 * };
 * }}}
 */
object AccelerometerData {
  import scodec.codecs._

  // Define Codec[X], where X is some type that can be turned into the
  // AccelerometerData and AccelerometerValue
  private type ZYX = (Int, Int, Int)
  private type CSU = (Int, Int, Unit)

  // you will want 40 bits, ignoring 1 bit, and then reading 13 bits each for x, y and z
  private implicit val packedAccelerometerData: Codec[ZYX] = ???

  // you will want 40 bits, reading 8 bits for SPS, 16 bits for count, and checking for constant 0xfefc
  private implicit val packedGfsHeader: Codec[CSU] = ???

  // decode the given ``bits`` into the tuple containing remaining bits and decoded List[AccelerometerData]
  private def decode(bits: BitVector): (BitVector, List[AccelerometerData]) = {
    // to collect (and then immediately throw away) the errors
    implicit val _ = scalaz.Monoid.instance[String](_ + _, "")

    val result /* \/[String, (BitVector, List[AccelerometerData])] */ = ???

    // We don't care about the errors, so we don't want \/[String, (BitVector, List[AccelerometerData])]
    // If we have -\/(e), we return (bits, Nil), if we have \/-((b, v)), we return (b, v)
    ???
  }

  /**
   * Decodes as much as possible from ``bits``, appending the values to ``ads``.
   * @param bits the input bit stream
   * @param ads the "current" list of ``AccelerometerData``
   * @return the remaining bits and decoded ``AccelerometerData``
   */
  // Hint: this will be @tailrec
  final def decodeAll(bits: BitVector, ads: List[AccelerometerData]): (BitVector, List[AccelerometerData]) = {
    decode(bits) match {
      // Parsed all we could, nothing remains
      // case (???, ???) => ???

      // Parsed all we could, but exactly `bits` remain => we did not get any further.
      // Repeated recursion will not solve anything.
      // case (???, ???) => ???

      // Still something left to parse
      // case (???, ???) => ???
      case _ => ???
    }
  }

}
