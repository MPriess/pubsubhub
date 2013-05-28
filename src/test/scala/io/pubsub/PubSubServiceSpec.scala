package io.pubsub

import spray.testkit.Specs2RouteTest
import spray.routing.HttpService
import org.specs2.mutable.Specification

object PubSubServiceSpec extends Specification with Specs2RouteTest with PubSubService {
  def actorRefFactory = system
    
  "The DemoService" should {

    "return a GET requests to the subscribe path" in {
      Get("/subscribe") ~> pubsub ~> check { entityAs[String] must contain("You wanna subscribe?") }
    }

    "return a GET requests to the publish path" in {
      Get("/publish") ~> pubsub ~> check { entityAs[String] must contain ("You wanna publish?") }
    }
  }
}