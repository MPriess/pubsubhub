package io.pubsub.domain

import scala.slick.driver.BasicDriver._

case class Publish(id: Option[Int], topic: String,
  hubUrl: String) {
  override def toString() = "publish topic: " + topic + " with puburl: " + hubUrl;
}

object Publisher extends Table[Publish]("publisher") {
    def id = column[Int]("publisher_id", O.PrimaryKey, O.AutoInc) // This is the primary key column
    def topic = column[String]("topic")
    def hubUrl = column[String]("hub_url")
    // Every table needs a * projection with the same type as the table's type parameter
    def * = id.? ~ topic ~ hubUrl <>(Publish, Publish.unapply _)
}