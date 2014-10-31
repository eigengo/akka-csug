package org.eigengo.akkacsug.hello

import akka.actor.{ActorSystem, Props}
import akka.util.Timeout

import scala.io.StdIn

object GreeterMain extends App {
  implicit val system = ActorSystem()
  import org.eigengo.akkacsug.hello.Greeter._
  import akka.actor.ActorDSL._
  import scala.concurrent.duration._
  import system.dispatcher
  implicit val timeout = Timeout(5.seconds)

  implicit val _ = actor(new Act {
    become {
      case x => println(">>> " + x)
    }
  })

  val greeter = system.actorOf(Props[Greeter])
  greeter ! Greet("Jan", "Hello")

  import akka.pattern.ask
  (greeter ? Greet("Jan", "Hello")) onSuccess {
    case x => println("??? " + x)
  }

  StdIn.readLine()
  system.shutdown()
}
