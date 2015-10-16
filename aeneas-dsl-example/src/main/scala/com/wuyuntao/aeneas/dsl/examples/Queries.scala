package com.wuyuntao.aeneas.dsl.examples

import java.util.{ Date, UUID }
import com.wuyuntao.aeneas.dsl.{ Column, Query } 

class UserByEmail extends Query {
  val id = Column[UUID](1)
  val email = Column[String](1)
  val password = Column[String](1)
}

class UserById extends Query {
  val id = Column[UUID](1)
  val email = Column[String](1)
  val password = Column[String](1)
  val lastLloginTtime = Column[Date](1)
}

class UserByName extends Query {
  val id = Column[UUID](1)
  val username = Column[String](1)
}