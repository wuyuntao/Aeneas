package com.wuyuntao.aeneas.tests.events

import com.wuyuntao.aeneas.Event

/**
 * @author Wu Yuntao
 */
class UserCreated extends Event {
  column[String]("email")
  column[String]("password")
  column[String]("username")
}