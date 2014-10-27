package org.eigengo.akkacsug.exercise

import akka.actor.Props
import akka.persistence.{SnapshotOffer, PersistentView}

class ExerciseView extends PersistentView {
  import org.eigengo.akkacsug.exercise.ExerciseProtocol._

  private var exercises: List[ClassifiedExercise] = Nil

  context.actorOf(Props(classOf[ExerciseClassifier], NaiveModel))
  context.actorOf(Props(classOf[ExerciseClassifier], WaveletModel))
  context.actorOf(Props(classOf[ExerciseClassifier], DynamicTimeWrappingModel))

  override def viewId: String = "exercise-view"

  override def persistenceId: String = "exercise-persistence"

  override def receive: Receive = {
    case SnapshotOffer(metadata, offeredSnapshot) =>
      exercises = offeredSnapshot.asInstanceOf[List[ClassifiedExercise]]
    case e@ExerciseDataEvt(data) if isPersistent =>
      context.actorSelection("*") ! e
    case e@ClassifiedExercise(confidence, exercise) =>
      println("Got " + e)
      if (confidence > 0.0) exercises = e :: exercises
      saveSnapshot(exercises)
    case GetExercises =>
      sender() ! exercises
  }
}
