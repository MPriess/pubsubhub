package io.pubsub

import org.specs2.mutable.Specification
import spray.testkit.Specs2RouteTest
import spray.http.ContentType
import spray.http.MediaType
import spray.http.MediaTypes
import com.sun.jndi.dns.Header
import spray.http.HttpBody
import spray.http.HttpHeaders._
import MediaTypes._
import spray.http.HttpEntity
import spray.http.HttpHeader

object PubSubServiceSpec extends Specification with Specs2RouteTest with PubSubService {
  def actorRefFactory = system

    "A GET request to the subscribe path" in {
      Get("/subscribe") ~> pubsub ~> check { entityAs[String] must contain("You wanna subscribe?") }
    }

    "A GET request to the publish path" in {
      Get("/publish") ~> pubsub ~> check { entityAs[String] must contain("You wanna publish?") }
    }

    "A POST request to the subscribe path" in {
      val header = List(`Content-Type`(`application/x-www-form-urlencoded`))
      val body = HttpEntity.apply("hub.callback=foo&hub.mode=bar&hub.topic=foo&hub.verify=foo&hub.lease_seconds=fsds&hub.secret=foo&hub.verify_token=bar")
      
      Post("/subscribe").withHeadersAndEntity(header, body) ~> pubsub ~> check { entityAs[String] must contain("subscribe") }
    }
}