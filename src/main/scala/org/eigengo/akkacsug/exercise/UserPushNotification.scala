package org.eigengo.akkacsug.exercise

import akka.actor.Actor
import com.notnoop.apns.APNS

object UserPushNotification {
  case class DefaultMessage(message: String)
}

class UserPushNotification extends Actor {
  import UserPushNotification._
  private val certificatePath = System.getProperty("user.home") + "/.ios/lift-push-development.p12"
  private val service = APNS.newService.withCert(certificatePath, "^Bantha P00d00$").withSandboxDestination.build

  override def receive: Receive = {
    case DefaultMessage(message) =>
      // lookup user device Id
      val deviceToken = "131af4f2 64f2c000 b5814833 90d01b87 f5cbd074 48bea21b 9b517640 97a5c74c"
      val payload = APNS.newPayload.alertBody(message).badge(1).sound("default").build
      service.push(deviceToken, payload)
  }
}
