package com.wuyuntao.aeneas.tests.events

import java.time.OffsetDateTime
import java.util.UUID

import com.wuyuntao.aeneas.Event

case class UserLoggedIn(val owner: UUID,
                        val revision: Int,
                        val timestamp: OffsetDateTime)
  extends Event