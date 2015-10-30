package com.wuyuntao.aeneas.dsl.example

import java.util.UUID
import com.wuyuntao.aeneas.dsl.{ Column, Event }

class UserCreated extends Event {
  val email = Column[String](1)
  val password = Column[String](1)
  val username = Column[String](1)
  //val gender = Column[String](2)
}

class UserLoggedIn extends Event {
  val ip = Column[IPAddress](1)
  //val device = Column[Device](2) 
}

class UserLoggedOut extends Event {
}

class PasswordChanged extends Event {
  val newPassword = Column[String](1)
}