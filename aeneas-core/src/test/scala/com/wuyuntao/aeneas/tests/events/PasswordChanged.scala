package com.wuyuntao.aeneas.tests.events

import com.wuyuntao.aeneas.Event
import com.wuyuntao.aeneas.QuerySet

/**
 * @author Wu Yuntao
 */
object PasswordChanged {
  val query = new QuerySet[PasswordChanged]()
}

class PasswordChanged extends Event {
  column[String]("newPassword")
}