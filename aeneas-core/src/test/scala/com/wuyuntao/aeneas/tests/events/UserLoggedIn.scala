package com.wuyuntao.aeneas.tests.events

import com.wuyuntao.aeneas.Event
import com.wuyuntao.aeneas.QuerySet
import com.wuyuntao.aeneas.tests.snapshots.AccountById

/**
 * @author Wu Yuntao
 */
object UserLoggedIn {
  val query = new QuerySet[UserLoggedIn]()
}

class UserLoggedIn extends Event {
  def onSave = {
    // Update last login time field of AccountsById
    val accountById = new AccountById
    accountById.owner = this.owner
    accountById.revision = this.revision
    accountById.lastLoginTime = this.time

    AccountById.query.update(accountById)
  }
}