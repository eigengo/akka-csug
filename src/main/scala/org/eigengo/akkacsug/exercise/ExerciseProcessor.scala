package org.eigengo.akkacsug.exercise

import akka.persistence.PersistentActor

class ExerciseProcessor extends PersistentActor {
  override def receiveRecover: Receive = ???

  override def receiveCommand: Receive = ???

  override def persistenceId: String = ???
}
