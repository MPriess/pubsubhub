package io.pubsub.domain

import scala.slick.driver.BasicDriver._

case class Subscribe(callback: String,
  mode: String,
  topic: String,
  verify: String,
  leaseSeconds: Int,
  secret: String,
  verifyToken: String) {
  override def toString() = "subscribe " + leaseSeconds;
}

  /** Creates an entry in the Subscriber table.
    *
    * @param id primarykey
    * @param pubId foreignkey to the published topic
    * @param callback callback url to the client.
    */
object Subscriber extends Table[(Int, Int, String)]("subscriber") {
    def id = column[Int]("sub_id", O.PrimaryKey) // This is the primary key column
    def pubId = column[Int]("pub_id")
    def callback = column[String]("callback")
    // Every table needs a * projection with the same type as the table's type parameter
    def * = id ~ pubId ~ callback
    
    def publisher = foreignKey("publisher_fk", pubId, Publisher)(_.id)
}