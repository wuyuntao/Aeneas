package com.wuyuntao.aeneas.tests.controllers

import java.util.UUID

import com.datastax.driver.core.utils.UUIDs
import com.wuyuntao.aeneas.tests.events.PasswordChanged
import com.wuyuntao.aeneas.tests.events.UserCreated
import com.wuyuntao.aeneas.tests.events.UserLoggedIn
import com.wuyuntao.aeneas.tests.events.UserLoggedOut
import com.wuyuntao.aeneas.tests.models.Account
import com.wuyuntao.aeneas.tests.snapshots.AccountsByEmail
import com.wuyuntao.aeneas.tests.snapshots.AccountsById
import com.wuyuntao.aeneas.tests.snapshots.AccountsByUsername

class AccountController {

  def register(email: String, password: String, username: String): Option[UUID] = {
    // Validate arguments...

    val emailExists = AccountsByEmail.query.exists(email)
    if (emailExists) {
      return None
    }

    val usernameExists = AccountsByUsername.query.exists(username)
    if (usernameExists) {
      return None
    }

    val id = UUIDs.timeBased
    val event = new UserCreated()
    event.id = id
    event.email = email
    event.password = password
    event.username = username

    Account.save(event)

    return Some(id)
  }

  def login(email: String, password: String): Option[AccountsById] = {
    val account = AccountsByEmail.query.get(email)
    if (account.isEmpty) {
      return None
    }

    if (account.get.password != password) {
      return None
    }

    val info = AccountsById.query.get(account.get.owner)
    if (info.isEmpty) {
      return None
    } else {
      val lastLoginTime = info.get.lastLoginTime

      val event = new UserLoggedIn()
      event.owner = info.get.owner

      Account.save(event)

      return Some(info.get)
    }
  }

  def changePassword(id: UUID, password: String, newPassword: String): Boolean = {
    val account = AccountsById.query.get(id)
    if (account.isEmpty) {
      return false
    }

    if (account.get.password != password) {
      return false
    }

    account.get.password = newPassword
    AccountsById.query.update(account.get)

    val event = new PasswordChanged()
    event.password = password
    event.newPassword = newPassword

    Account.save(event)

    return true
  }

  def logout(id: UUID): Boolean = {
    val account = AccountsById.query.get(id)
    if (account.isEmpty) {
      return false
    }

    val event = new UserLoggedOut()
    event.owner = id

    Account.save(event)

    return true
  }
}