package io.pubsub.persistence

import io.pubsub.domain.Publisher
import io.pubsub.domain.Subscriber
import io.pubsub.domain.Publish

object PublisherDaoTest extends App {
    
    val publisherDao = new PublisherDao

    val publish = Publish(None, "testTopic", "http://localhost:8080/published/content")

    publisherDao.save(publish);
    //Subscriber.insert(1, 1, "http://localhost:8080/call/me/baby")

    println("Publisher:")
    publisherDao.findAll foreach {
      case (topic) =>
        println(" topic: " + topic)
    }
}