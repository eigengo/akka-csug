package org.eigengo.akkacsug.exercise

import akka.actor.Actor.Receive
import akka.actor.Props
import akka.persistence.{PersistentView, SnapshotOffer}

/**
 * View that handles processing the events, delegates to the classifiers,
 * and provides the query functions.
 */
class UserExerciseView extends PersistentView {
  import UserExerciseProtocol._
  import UserPushNotification._
  import actors._

  override def viewId: String = ???

  override def persistenceId: String = ???

  override def receive: Receive = ???
}
