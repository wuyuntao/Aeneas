package com.wuyuntao.aeneas.tests.events

import com.wuyuntao.aeneas.Event
import com.wuyuntao.aeneas.QuerySet
import com.wuyuntao.aeneas.tests.snapshots.AccountByEmail
import com.wuyuntao.aeneas.tests.snapshots.AccountById

/**
 * @author Wu Yuntao
 */
object PasswordChanged {
  val query = new QuerySet[PasswordChanged]()
}

class PasswordChanged extends Event {
  column[String]("newPassword")

  def onSave = {
    // Update password fields in snapshots
    val accountById = new AccountById
    accountById.owner = this.owner
    accountById.revision = this.revision
    accountById.password = this.password

    AccountById.query.update(accountById)

    val accountByEmail = new AccountByEmail()
    accountByEmail.owner = this.owner
    accountByEmail.revision = this.revision
    accountByEmail.password = this.password

    AccountByEmail.query.update(accountByEmail)
  }
}