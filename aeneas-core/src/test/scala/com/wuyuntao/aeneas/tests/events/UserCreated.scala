package com.wuyuntao.aeneas.tests.events

import com.wuyuntao.aeneas.Event
import com.wuyuntao.aeneas.QuerySet

/**
 * @author Wu Yuntao
 */
object UserCreated {
  val query = new QuerySet[UserCreated]()
}

class UserCreated extends Event {
  column[String]("email")
  column[String]("password")
  column[String]("username")
}