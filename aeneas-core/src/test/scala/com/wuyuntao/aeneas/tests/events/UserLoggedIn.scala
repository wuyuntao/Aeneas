package com.wuyuntao.aeneas.tests.events

import com.wuyuntao.aeneas.Event
import com.wuyuntao.aeneas.QuerySet

/**
 * @author Wu Yuntao
 */
object UserLoggedIn {
  val query = new QuerySet[UserLoggedIn]()
}

class UserLoggedIn extends Event {
  
}