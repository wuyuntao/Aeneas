package com.wuyuntao.aeneas.dsl.examples

import com.wuyuntao.aeneas.dsl.{ Command, Column }

class RegisterNewAccount extends Command {
  val email = Column[String](1)
  val password = Column[String](1)
  val passwordConfirmation = Column[String](1)
  val username = Column[String](1)
  
  val gender = Column[String](2)
}

class LoginAccount extends Command {
  val email = Column[String](1)
  val password = Column[String](1)
  
  val ip = Column[IPAddress](1)
  val device = Column[Device](2)
}

class LogoutAccount extends Command {
}