package com.wuyuntao.aeneas

import java.time.OffsetDateTime
import java.util.UUID

/**
 * @author Wu Yuntao
 */
abstract trait Event {
  val owner: UUID
  val revision: Int
  val timestamp:OffsetDateTime 
}