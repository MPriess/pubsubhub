package io.pubsub

import spray.routing._
import spray.routing.SimpleRoutingApp
import akka.actor.Actor
import io.pubsub.domain.Subscribe
import io.pubsub.domain.Publish
import spray.http.StatusCode
import spray.http.StatusCodes
import org.fusesource.scalate.TemplateEngine
import spray.http._
import spray.http.ContentType._
import spray.http.MediaTypes._
import io.pubsub.persistence.PubSubDao

trait PubSubService extends HttpService {

  val templateEngine = new TemplateEngine
  val publisherDao = new PubSubDao

  val pubsub = {
    path("subscribe") {
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
              StatusCodes.Accepted
            }
          }
      }
    } ~
      path("publish") {
        post { // New content notification
          formFields(
            "hud.mode"?,
            "hub.url").as(Publish) { publish =>
              complete {
                publisherDao.save(publish)
                StatusCodes.NoContent
              }
            }
        }
      } ~
      path("admin") {
        get {
          complete {
            HttpEntity(`text/html`, templateEngine.layout("/layouts/admin.ssp",Map("topics" -> publisherDao.findAll)))
          }
        }
      } ~
      path("admin" / "publish") {
        get {
          complete {
            HttpEntity(`text/html`, templateEngine.layout("/layouts/publish.ssp"))
          }
        }
      } ~
      path("admin" / "subscribe") {
        get {
          complete {
            HttpEntity(`text/html`, templateEngine.layout("/layouts/subscribe.ssp"))
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