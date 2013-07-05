package io.pubsub.domain

case class Subscribe(callback: String,
  mode: String,
  topic: String,
  verify: String,
  leaseSeconds: Int,
  secret: String,
  verifyToken: String) {
  override def toString() = "subscribe " + leaseSeconds;
}