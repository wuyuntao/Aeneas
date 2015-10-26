package com.wuyuntao.aeneas.tests.commands

import java.time.OffsetDateTime
import java.util.UUID

import com.wuyuntao.aeneas.Command

case class Register(val owner: UUID,
                    val timestamp: OffsetDateTime,
                    val email: String,
                    val password: String,
                    val passwordConfirmation: String,
                    val username: String,
                    val gender: String)
  extends Command {

  // Version: 1
  def this(owner: UUID, timestamp: OffsetDateTime, email: String, password: String, passwordConfirmation: String, username: String) = {
    this(owner, timestamp, email, password, passwordConfirmation, username, "Unknown")
  }

}