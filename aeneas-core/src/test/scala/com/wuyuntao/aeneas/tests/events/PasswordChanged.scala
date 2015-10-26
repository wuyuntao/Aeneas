package com.wuyuntao.aeneas.tests.events

import java.time.OffsetDateTime
import java.util.UUID

import com.wuyuntao.aeneas.Event
import com.wuyuntao.aeneas.QuerySet

class PasswordChanged(val owner: UUID,
                      val revision: Int,
                      val timestamp: OffsetDateTime,
                      val newPassword: String)
  extends Event