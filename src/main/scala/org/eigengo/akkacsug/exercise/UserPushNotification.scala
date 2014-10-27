package org.eigengo.akkacsug.exercise

import akka.actor.Actor
import com.notnoop.apns.APNS

import scala.io.Source

object UserPushNotification {
  case class DefaultMessage(message: String)
}

class UserPushNotification extends Actor {
  import UserPushNotification._
  private val userHomeIos = System.getProperty("user.home") + "/.ios"
  private val certificatePath = s"$userHomeIos/lift-push-development.p12"
  private val certificatePassword = Source.fromFile(s"$userHomeIos/lift-push-development.pwd").mkString
  private val service = APNS.newService.withCert(certificatePath, certificatePassword).withSandboxDestination.build

  override def receive: Receive = {
    case DefaultMessage(message) =>
      // lookup user device Id
      val deviceToken = "131af4f2 64f2c000 b5814833 90d01b87 f5cbd074 48bea21b 9b517640 97a5c74c"
      val payload = APNS.newPayload.alertBody(message).badge(1).sound("default").build
      service.push(deviceToken, payload)
  }
}
