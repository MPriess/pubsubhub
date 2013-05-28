package io.pubsub

import org.specs2.mutable.Specification

import spray.testkit.Specs2RouteTest

object PubSubServiceSpec extends Specification with Specs2RouteTest with PubSubService {
  def actorRefFactory = system

    "A GET request to the subscribe path" in {
      Get("/subscribe") ~> pubsub ~> check { entityAs[String] must contain("You wanna subscribe?") }
    }

    "A GET request to the publish path" in {
      Get("/publish") ~> pubsub ~> check { entityAs[String] must contain("You wanna publish?") }
    }

    "A POST request to the publish path" in {
      Post("/publish").withHeaders(headers) ~> pubsub ~> check { entityAs[String] must contain("foo, bar") }
    }
}