package com.wuyuntao.aeneas.dsl.example

import com.wuyuntao.aeneas.dsl.{ Snapshot, Column }

class User extends Snapshot {
  val email = Column[String](1)
  val password = Column[String](1)
  val username = Column[String](1)
}