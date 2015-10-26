package com.wuyuntao.aeneas.tests.views

import java.time.OffsetDateTime
import java.util.UUID

import com.wuyuntao.aeneas.View

case class UserById(val id: UUID, val email: String, val lastLoginTime: OffsetDateTime) extends View