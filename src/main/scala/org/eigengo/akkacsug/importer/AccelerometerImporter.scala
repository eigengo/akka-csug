package org.eigengo.akkacsug.importer

import akka.actor.Actor
import org.eigengo.akkacsug.AccelerometerData
import scodec.bits.BitVector

/**
 * Collects the received ``BitVector``s, and replies when ``List[AccelerometerData]`` once
 * it has sufficient bits to decode.
 *
 * If the received bits are not yet sufficient, it buffers the bits
 */
class AccelerometerImporter extends Actor {
  var buffer: BitVector = BitVector.empty

  // append the bits, decode, update state, and reply with the decoded List[AccelerometerData]
  override def receive: Receive = {
    ???
  }

}
