package org.eigengo.akkacsug

import akka.actor.Actor

object Greeter {

  case class Greet(person: String, greeting: String)

}

class Greeter extends Actor {
  import Greeter._

  def receive: Receive = {
    case Greet(p, g) => sender() ! s"$g $p"
  }
}
