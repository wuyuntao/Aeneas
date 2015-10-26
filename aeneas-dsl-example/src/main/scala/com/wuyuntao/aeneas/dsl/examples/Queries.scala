package com.wuyuntao.aeneas.dsl.examples

import java.util.{ Date, UUID }
import com.wuyuntao.aeneas.dsl.{ Column, Query }

class GetUserByEmail extends Query {
  val email = Column[String](1)
}

class GetUserById extends Query {
  val id = Column[UUID](1)

}

class GetUserByName extends Query {
  val username = Column[String](1)
}