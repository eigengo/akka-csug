package org.eigengo.akkacsug.exercise

import akka.persistence.PersistentView
import org.eigengo.akkacsug.AccelerometerData

class ExerciseView extends PersistentView {
  override def viewId: String = "exercise-view"

  override def persistenceId: String = "exercise-persistence"

  override def receive: Receive = {
    case AccelerometerData(samplingRate, values) if isPersistent =>

  }
}
