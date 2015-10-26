package com.wuyuntao.aeneas.tests.queries

import java.time.OffsetDateTime
import java.util.UUID

import com.wuyuntao.aeneas.Query

class GetUserByName(val owner: UUID, val timestamp: OffsetDateTime, val username: String) extends Query