package com.wuyuntao.aeneas.tests.commands

import java.time.OffsetDateTime
import java.util.UUID

import com.wuyuntao.aeneas.Command

case class Logout(val owner: UUID,
                  val timestamp: OffsetDateTime)
  extends Command