package io.pubsub

import spray.routing._
import spray.routing.SimpleRoutingApp
import akka.actor.Actor
import io.pubsub.domain.Subscribe
import spray.http.StatusCode
import spray.http.StatusCodes
import org.fusesource.scalate.TemplateEngine
import spray.http._
import spray.http.ContentType._
import spray.http.MediaTypes._

trait PubSubService extends HttpService  {
  
  val templateEngine = new TemplateEngine

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
            HttpEntity(`text/html`, templateEngine.layout("/layouts/subscribe.ssp"))
          }
        } ~
          post {
            // Publish and subscribe
            formFields("hub.callback",
              "hub.mode",
              "hub.topic",
              "hub.verify",
              "hub.lease_seconds" ? 200,
              "hub.secret" ? "0000",
              "hub.verify_token" ? "0000").as(Subscribe) { subscribe =>
                complete {
                  StatusCodes.NoContent
                }
              }
          }
      } ~
      post { // New content notification
        formFields(
         "hub.mode",
          "hub.url")
            complete {
              StatusCodes.NoContent
          }
      } ~
      path("publish") {
        get {
          complete {
            HttpEntity(`text/html`, templateEngine.layout("/layouts/publish.ssp"))
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