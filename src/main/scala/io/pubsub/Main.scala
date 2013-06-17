package io.pubsub

import akka.io.IO
import spray.can.Http
import akka.actor.{ActorSystem, Props}

object Main extends App {
  implicit val system = ActorSystem()
  
  val handler = system.actorOf(Props(new PubSubActor), name = "handler")
  
  IO(Http) ! Http.Bind(handler, interface = "localhost", port = 8080)
}