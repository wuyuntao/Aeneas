package com.wuyuntao.aeneas

import java.util.UUID

abstract trait Snapshot {
  val owner: UUID
  val revision: Int
}