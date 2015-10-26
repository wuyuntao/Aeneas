package com.wuyuntao.aeneas.dsl.examples

import com.wuyuntao.aeneas.dsl.{ Command, Column }

class Register extends Command {
  val email = Column[String](1)
  val password = Column[String](1)
  val passwordConfirmation = Column[String](1)
  val username = Column[String](1)
  
  val gender = Column[String](2)
}

class Login extends Command {
  val email = Column[String](1)
  val password = Column[String](1)
  
  val ip = Column[IPAddress](1)
  val device = Column[Device](2)
}

class Logout extends Command {
}

class ChangePassword extends Command {
  val currentPassword = Column[String](1)
  val newPassword = Column[String](1)
  val newPasswordConfirmation = Column[String](1)
}