package org.eigengo.akkacsug.hello

import akka.actor.Actor

object Greeter {

  case class Greet(person: String, greeting: String)

}

class Greeter extends Actor {
  import org.eigengo.akkacsug.hello.Greeter._

  def receive: Receive = {
    case Greet(p, g) => sender() ! s"$g $p"
  }
}
