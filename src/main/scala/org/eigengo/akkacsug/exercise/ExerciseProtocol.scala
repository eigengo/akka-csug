package org.eigengo.akkacsug.exercise

import org.eigengo.akkacsug.AccelerometerData
import scodec.bits.BitVector

object ExerciseProtocol {

  type Exercise = String

  case class ExerciseDataCmd(bits: BitVector)

  case class ExerciseDataEvt(data: List[AccelerometerData])

  case class ClassifiedExercise(confidence: Double, exercise: Option[Exercise])

  case object GetExercises

}
