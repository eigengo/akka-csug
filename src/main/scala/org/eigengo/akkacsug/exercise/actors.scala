package org.eigengo.akkacsug.exercise

import akka.actor.{ActorSelection, ActorRefFactory}

/**
 * Groups the actors so that their names do not diverge; it also contains convenience functions for actor lookups
 */
object actors {

  /**
   * The UserPushNotification definition.
   */
  object pushNotification {
    /** The actor name */
    val name = "push-notification"
    /** The lookup function */
    def lookup(implicit arf: ActorRefFactory): ActorSelection = arf.actorSelection(s"/user/$name")
  }

}
