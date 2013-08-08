package io.pubsub.persistence

import scala.slick.driver.H2Driver.simple._
import scala.slick.driver.H2Driver.simple.Database.threadLocalSession
import io.pubsub.domain.Subscribe
import io.pubsub.domain.Publish
import io.pubsub.domain.Publisher
import io.pubsub.domain.Subscriber
import io.pubsub.domain.Publish
import scala.slick.jdbc.meta.MTable

class PubSubDao {

  private val db = Database.forURL("jdbc:h2:mem:test1;DB_CLOSE_DELAY=-1", driver = "org.h2.Driver");

  db withSession {
      (Publisher.ddl ++ Subscriber.ddl).create
  }

  /**
   * Add or update a publisher
   */
  def save(publish: Publish) { 
      db withSession {
        Publisher.insert(publish)
      }
  }

  /**
   * List all published topics
   */
  def findAll(): List[Publish] = {
    db withSession {
      Query(Publisher).list
    }
  }

}