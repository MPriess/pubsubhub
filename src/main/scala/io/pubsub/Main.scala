package io.pubsub

import spray.can.server.SprayCanHttpServerApp
import akka.actor.Props

object Main extends App with SprayCanHttpServerApp {
  val handler = system.actorOf(Props[PubSubActor])
  newHttpServer(handler) ! Bind(interface = "localhost", port = 8080)
}