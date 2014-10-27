package org.eigengo.akkacsug.exercise

import akka.actor.Actor
import org.eigengo.akkacsug.AccelerometerData
import org.eigengo.akkacsug.exercise.ExerciseProtocol.Exercise

import scala.util.Random

sealed trait ExerciseModel {
  import ExerciseProtocol._

  def apply(data: AccelerometerData): (Double, Option[Exercise])
}
case object WaveletModel extends ExerciseModel {
  override def apply(data: AccelerometerData): (Double, Option[Exercise]) = (0.0, None)
}
case object DynamicTimeWrappingModel extends ExerciseModel {
  override def apply(data: AccelerometerData): (Double, Option[Exercise]) = (0.0, None)
}
case object NaiveModel extends ExerciseModel {
  override def apply(data: AccelerometerData): (Double, Option[Exercise]) = (1.0, Some("Goku is my spotter"))
}

class ExerciseClassifier(model: ExerciseModel) extends Actor {
  import org.eigengo.akkacsug.exercise.ExerciseProtocol._

  override def receive: Receive = {
    case ExerciseDataEvt(data) if data.nonEmpty =>
      Thread.sleep(300 + Random.nextInt(1000))
      val ad = data.foldRight(data.last)((res, ad) => ad.copy(values = ad.values ++ res.values))
      val (confidence, exercise) = model(ad)

      sender() ! ClassifiedExercise(confidence, exercise)
  }

}
