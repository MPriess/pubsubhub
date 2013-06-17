package io.pubsub

import spray.routing._
import spray.routing.SimpleRoutingApp
import akka.actor.Actor
import io.pubsub.domain.Subscribe

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
        } ~
        post {
          // form extraction from multipart or www-url-encoded forms
          formFields("hub.callback",
            "hub.mode",
            "hub.topic",
            "hub.verify",
            "hub.lease_seconds",
            "hub.secret",
            "hub.verify_token").as(Subscribe) { subscribe =>
                complete {
                  subscribe.toString
                }
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