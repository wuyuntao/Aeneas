package com.wuyuntao.aeneas.tests.queries

import java.time.OffsetDateTime
import java.util.UUID

import com.wuyuntao.aeneas.Query

case class GetUserByEmail(val owner: UUID, timestamp : OffsetDateTime, email: String) extends Query