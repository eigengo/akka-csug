package org.eigengo.akkacsug.hello

import akka.actor.{ActorSystem, Props}

import scala.io.StdIn

object GreeterMain extends App {
  implicit val system = ActorSystem()
  import org.eigengo.akkacsug.hello.Greeter._

  val greeter = system.actorOf(Props[Greeter])
  greeter ! Greet("Jan", "Hello")

  StdIn.readLine()
  system.shutdown()
}
