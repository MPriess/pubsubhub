package io.pubsub

import org.specs2.mutable.Specification
import spray.testkit.Specs2RouteTest
import spray.http.ContentType._
import spray.http.MediaTypes._
import spray.http.HttpBody
import spray.http.HttpEntity
import spray.http.ContentType
import spray.http.StatusCodes


object PubSubServiceSpec extends Specification with Specs2RouteTest with PubSubService {
  def actorRefFactory = system
    
    "A POST request to the subscribe path" in {
      val body = HttpEntity.apply(`application/x-www-form-urlencoded`, "hub.callback=foo&hub.mode=subscribe&hub.topic=foo&hub.verify=async")
      
      Post("/subscribe").withEntity(body) ~> pubsub ~> check { status mustEqual StatusCodes.Accepted }
    }
}