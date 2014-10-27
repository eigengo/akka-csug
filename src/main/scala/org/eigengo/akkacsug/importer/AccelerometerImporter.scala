package org.eigengo.akkacsug.importer

import akka.actor.Actor
import org.eigengo.akkacsug.AccelerometerData
import scodec.bits.BitVector

class AccelerometerImporter extends Actor {
  var buffer: BitVector = BitVector.empty
  import AccelerometerData._

  override def receive: Receive = {
    case bits: BitVector =>
      val (bits2, ads) = parseAll(buffer ++ bits, Nil)
      buffer = bits2
      sender() ! ads
  }

}
