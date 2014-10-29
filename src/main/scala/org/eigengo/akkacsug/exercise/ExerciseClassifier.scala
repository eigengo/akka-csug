package org.eigengo.akkacsug.exercise

import akka.actor.Actor
import org.eigengo.akkacsug.AccelerometerData
import org.eigengo.akkacsug.exercise.UserExerciseProtocol.ClassifiedExercise

import scala.util.Random

/**
 * The exercise classification model
 */
sealed trait ExerciseModel {
  def apply(data: AccelerometerData): ClassifiedExercise
}

/**
 * Implementation left as an exercise
 */
case object WaveletModel extends ExerciseModel {
  override def apply(data: AccelerometerData): ClassifiedExercise = ClassifiedExercise(0.0, None)
}

/**
 * Implementation left as an exercise
 */
case object DynamicTimeWrappingModel extends ExerciseModel {
  override def apply(data: AccelerometerData): ClassifiedExercise = ClassifiedExercise(0.0, None)
}

/**
 * This is the only implementation I can have a go at!
 */
case object NaiveModel extends ExerciseModel {
  override def apply(data: AccelerometerData): ClassifiedExercise = ClassifiedExercise(1.0, Some("Goku was your spotter"))
}

/**
 * Match the received exercise data using the given model
 * @param model the model
 */
class ExerciseClassifier(model: ExerciseModel) extends Actor {
  import UserExerciseProtocol._

  override def receive: Receive = {
    ???
  }

}
