package com.wuyuntao.aeneas.dsl.example

import java.time.OffsetDateTime
import java.util.UUID

import com.wuyuntao.aeneas.dsl.Column
import com.wuyuntao.aeneas.dsl.View

class UserByEmail extends View {
  val email = Column[String](1)
  val id = Column[UUID](1)
}

class UserById extends View {
  val id = Column[UUID](1)
  val email = Column[String](1)
  val lastLoginTtime = Column[OffsetDateTime](1)
}

class UserByName extends View {
  val username = Column[String](1)
  val id = Column[UUID](1)
}