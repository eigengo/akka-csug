package org.eigengo.akkacsug.exercise

import akka.actor.Actor
import akka.persistence.PersistentActor
import org.eigengo.akkacsug.AccelerometerData
import scodec.bits.BitVector

class ExerciseProcessor extends PersistentActor {
  private var buffer: BitVector = BitVector.empty
  import AccelerometerData._
  import ExerciseProtocol._

  private def validateData(data: List[AccelerometerData]): Boolean = data match {
    case Nil  => true
    case h::t => data.forall(_.samplingRate == h.samplingRate)
  }

  override def persistenceId: String = "exercise-persistence"

  override def receiveRecover: Receive = Actor.emptyBehavior

  override def receiveCommand: Receive = {
    case ExerciseDataCmd(bits) =>
      val (bits2, data) = parseAll(buffer ++ bits, Nil)
      if (validateData(data)) {
        buffer = bits2
        persistAsync(ExerciseDataEvt(data)) { e =>
          // noop
        }
      }


  }

}
