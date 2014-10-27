package org.eigengo.akkacsug.exercise

import akka.actor.Props
import akka.persistence.{SnapshotOffer, PersistentView}

/**
 * View that handles processing the events, delegates to the classifiers,
 * and provides the query functions.
 */
class UserExerciseView extends PersistentView {
  import org.eigengo.akkacsug.exercise.UserExerciseProtocol._

  private var exercises: List[ClassifiedExercise] = Nil

  context.actorOf(Props(classOf[ExerciseClassifier], NaiveModel))
  context.actorOf(Props(classOf[ExerciseClassifier], WaveletModel))
  context.actorOf(Props(classOf[ExerciseClassifier], DynamicTimeWrappingModel))

  override val viewId: String = "user-exercise-view"

  override val persistenceId: String = "user-exercise-persistence"

  override def receive: Receive = {
    // Remember to handle snapshot offers when using ``saveSnapshot``
    case SnapshotOffer(metadata, offeredSnapshot) =>
      exercises = offeredSnapshot.asInstanceOf[List[ClassifiedExercise]]

    // send the exercise to be classified to the children
    case e@ExerciseDataEvt(data) if isPersistent =>
      context.actorSelection("*") ! e

    // classification received
    case e@ClassifiedExercise(confidence, exercise) =>
      if (confidence > 0.0) exercises = e :: exercises
      saveSnapshot(exercises)

    // query for exercises
    case GetExercises =>
      sender() ! exercises
  }
}
