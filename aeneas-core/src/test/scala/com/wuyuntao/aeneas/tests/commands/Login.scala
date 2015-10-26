package com.wuyuntao.aeneas.tests.commands

import java.time.OffsetDateTime
import java.util.UUID

import com.wuyuntao.aeneas.Command
import com.wuyuntao.aeneas.tests.types.Device
import com.wuyuntao.aeneas.tests.types.IPAddress

case class Login(val owner: UUID,
                 val timestamp: OffsetDateTime,
                 val email: String,
                 val password: String,
                 val ip: IPAddress,
                 val device: Device)
  extends Command {

  // Version: 1
  def this(owner: UUID, timestamp: OffsetDateTime, email: String, password: String, ip: IPAddress) = {
    this(owner, timestamp, email, password, ip, null)
  }

}