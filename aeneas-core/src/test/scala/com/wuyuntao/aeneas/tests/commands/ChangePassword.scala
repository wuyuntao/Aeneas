package com.wuyuntao.aeneas.tests.commands

import java.time.OffsetDateTime
import java.util.UUID

import com.wuyuntao.aeneas.Command

case class ChangePassword(val owner: UUID,
                          val timestamp: OffsetDateTime,
                          val currentPassword: String,
                          val newPassword: String,
                          val newPasswordConfirmation: String)
  extends Command