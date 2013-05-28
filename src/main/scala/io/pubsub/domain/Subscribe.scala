package io.pubsub.domain

case class Subscribe(callback: String,
                     mode: String,
                     topic: String,
                     verify: String,
                     leaseSeconds: String,
                     secret: String,
                     verifyToken: String)