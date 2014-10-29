package org.eigengo.akkacsug.hello

import akka.actor.Actor
import akka.actor.Actor.Receive

object Greeter {

  case class Greet(person: String, greeting: String)

}

class Greeter extends Actor {

  // handle the message from the Greeter companion
  override def receive: Receive = ???
}
