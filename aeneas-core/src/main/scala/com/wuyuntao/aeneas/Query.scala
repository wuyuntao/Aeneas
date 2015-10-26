package com.wuyuntao.aeneas

import java.time.OffsetDateTime
import java.util.UUID

abstract trait Query {
  val owner: UUID
  val timestamp: OffsetDateTime
}