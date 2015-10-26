package com.wuyuntao.aeneas.tests.queries

import java.time.OffsetDateTime
import java.util.UUID

import com.wuyuntao.aeneas.Query

case class GetUserById(val owner: UUID, val timestamp: OffsetDateTime, val userId: UUID) extends Query