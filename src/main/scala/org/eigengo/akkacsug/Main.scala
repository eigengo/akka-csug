package org.eigengo.akkacsug

import akka.actor.{Props, ActorSystem}

import scala.io.StdIn

object Main extends App {
  implicit val system = ActorSystem()
  import Greeter._

  val greeter = system.actorOf(Props[Greeter])
  greeter ! Greet("Jan", "Hello")

  StdIn.readLine()
  system.shutdown()
}
