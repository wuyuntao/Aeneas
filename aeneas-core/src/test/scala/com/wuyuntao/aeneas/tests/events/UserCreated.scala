package com.wuyuntao.aeneas.tests.events

import java.time.OffsetDateTime
import java.util.UUID

import com.wuyuntao.aeneas.Event

case class UserCreated(val owner: UUID,
                       val revision: Int,
                       val timestamp: OffsetDateTime,
                       val email: String,
                       val password: String,
                       val username: String,
                       val gender: String)
  extends Event {

  // Version: 1
  def this(owner: UUID, revision: Int, timestamp: OffsetDateTime, email: String, password: String, username: String) = {
    this(owner, revision, timestamp, email, password, username, "Unknown")
  }
}