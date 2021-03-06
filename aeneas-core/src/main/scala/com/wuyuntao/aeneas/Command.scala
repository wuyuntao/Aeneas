package com.wuyuntao.aeneas

import java.time.OffsetDateTime
import java.util.UUID

abstract trait Command {
  val owner: UUID
  val timestamp: OffsetDateTime
}