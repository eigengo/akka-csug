package org.eigengo.akkacsug.exercise

import akka.actor.Actor
import akka.persistence.PersistentActor
import org.eigengo.akkacsug.AccelerometerData
import scodec.bits.BitVector

/**
 * Processes the exercise data commands by parsing the bits and then generating the
 * appropriate events.
 */
class UserExerciseProcessor extends PersistentActor {
  import AccelerometerData._
  import UserExerciseProtocol._

  override def receiveRecover: Receive = ???

  override def receiveCommand: Receive = ???

  override def persistenceId: String = ???
}
