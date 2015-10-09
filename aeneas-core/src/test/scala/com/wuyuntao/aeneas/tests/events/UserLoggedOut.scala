package com.wuyuntao.aeneas.tests.events

import com.wuyuntao.aeneas.Event
import com.wuyuntao.aeneas.QuerySet

/**
 * @author Wu Yuntao
 */
object UserLoggedOut {
  val query = new QuerySet[UserLoggedOut]()
}

class UserLoggedOut extends Event {
  def onSave = {
    
  }
}