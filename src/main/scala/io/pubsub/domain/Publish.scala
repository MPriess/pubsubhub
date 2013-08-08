package io.pubsub.domain

import scala.slick.driver.BasicDriver._

case class Publish(id: Option[Int], hubUrl: String) {
  override def toString() = "puburl: " + hubUrl;
}

object Publisher extends Table[Publish]("publisher") {
    def id = column[Int]("publisher_id", O.PrimaryKey, O.AutoInc) // This is the primary key column
    def hubUrl = column[String]("hub_url")
    // Every table needs a * projection with the same type as the table's type parameter. Define the projection maybe in another way
    // and remove the id which is 
    def * = id.? ~ hubUrl <>(Publish, Publish.unapply _)
}