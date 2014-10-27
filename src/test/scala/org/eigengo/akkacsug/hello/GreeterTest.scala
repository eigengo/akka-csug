package org.eigengo.akkacsug.hello

import akka.actor.ActorSystem
import akka.testkit.{ImplicitSender, TestActorRef, TestKitBase}
import org.scalacheck.Prop.forAll
import org.scalacheck.Properties

object GreeterTest extends Properties("Greeter") with TestKitBase with ImplicitSender {
  override implicit lazy val system: ActorSystem = ActorSystem()
  val greeter = TestActorRef[Greeter]

  property("Greetings") = forAll { (person: String, greeting: String) =>
    greeter ! Greeter.Greet(person, greeting)
    expectMsgType[String] == s"$greeting $person"
  }

}
