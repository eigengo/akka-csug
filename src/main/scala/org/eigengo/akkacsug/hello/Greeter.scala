package org.eigengo.akkacsug.hello

import akka.actor.Actor

object Greeter {

  case class Greet(person: String, greeting: String)

}

class Greeter {

  // define this to be an actor, and handle the message from
  // the Greeter companion

}
