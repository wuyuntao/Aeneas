package com.wuyuntao.aeneas

import java.time.OffsetDateTime
import java.util.UUID

/**
 * @author Wu Yuntao
 */
abstract trait Event extends Table {
  column[UUID]("owner")
  column[Int]("revision")
  column[OffsetDateTime]("time")
}