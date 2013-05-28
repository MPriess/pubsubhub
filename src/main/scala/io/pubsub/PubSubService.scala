package io.pubsub

import spray.routing._
import spray.routing.SimpleRoutingApp
import spray.can.server.HttpServer
import akka.actor.Actor

trait PubSubService extends HttpService {
  
  val pubsub = {
    path("") {
      get {
        complete {
          <h1>Welcome</h1>
        }
      }
    } ~
      path("subscribe") {
        get {
          complete {
            <h1>You wanna subscribe?</h1>
          }
        }
      } ~
      path("publish") {
        get {
          complete {
            <h1>You wanna publish?</h1>
          }
        }
      } ~
      path("documentation") {
        get {
          complete {
            <h1>Documentation</h1>
          }
        }
      }
  }
}