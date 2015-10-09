package com.wuyuntao.aeneas.tests.events

import com.wuyuntao.aeneas.Event
import com.wuyuntao.aeneas.QuerySet
import com.wuyuntao.aeneas.tests.snapshots.AccountByUsername
import com.wuyuntao.aeneas.tests.snapshots.AccountByEmail
import com.wuyuntao.aeneas.tests.snapshots.AccountById

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

  def onSave = {
    // Create all snapshots of account
    val accountById = new AccountById
    accountById.owner = this.owner
    accountById.revision = this.revision
    accountById.username = this.username
    accountById.password = this.password
    accountById.lastLoginTime = this.time

    AccountById.query.create(accountById)

    val accountByEmail = new AccountByEmail()
    accountByEmail.owner = this.owner
    accountByEmail.revision = this.revision
    accountByEmail.email = this.email
    accountByEmail.password = this.password

    AccountByEmail.query.create(accountByEmail)

    val accountByUsername = new AccountByUsername
    accountByUsername.owner = this.owner
    accountByUsername.revision = this.revision
    accountByUsername.username = this.username

    AccountByUsername.query.create(accountByUsername)
  }
}