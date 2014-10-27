package org.eigengo.akkacsug.exercise

import akka.actor.{ActorSystem, Props}
import scodec.bits.BitVector

import scala.io.StdIn

object ExerciseMain extends App {
  import org.eigengo.akkacsug.exercise.ExerciseProtocol._
  import akka.actor.ActorDSL._
  implicit val system = ActorSystem()
  implicit val _ = actor(new Act {
    become {
      case x => println(s">>> $x")
    }
  })
  val processor = system.actorOf(Props[ExerciseProcessor])
  val view = system.actorOf(Props[ExerciseView])

  val is = getClass.getResourceAsStream("/training/chest1.dat")
  val bits = BitVector.fromInputStream(is)
  processor ! ExerciseDataCmd(bits)

  view ! GetExercises

  StdIn.readLine()
  system.shutdown()
}
