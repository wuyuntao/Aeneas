package com.wuyuntao.aeneas

import java.util.UUID

/**
 * @author Wu Yuntao
 */
trait Snapshot extends Table {
  column[UUID]("owner")
  column[Int]("revision")
}