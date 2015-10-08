package com.wuyuntao.aeneas.tests

import java.util.UUID

import com.wuyuntao.aeneas.tests.events.PasswordChanged
import com.wuyuntao.aeneas.tests.events.UserCreated
import com.wuyuntao.aeneas.tests.events.UserLoggedIn
import com.wuyuntao.aeneas.tests.events.UserLoggedOut
import com.wuyuntao.aeneas.tests.snapshots.AccountsByEmail
import com.wuyuntao.aeneas.tests.snapshots.AccountsById
import com.wuyuntao.aeneas.tests.snapshots.AccountsByUsername

class LoginController {
  def register(email: String, password: String, username: String): String = {
    // Validate arguments...

    val emailExists = AccountsByEmail.query.exists(email)
    if (emailExists) {
      return "Email exists"
    }

    val usernameExists = AccountsByUsername.query.exists(username)
    if (usernameExists) {
      return "Username exists"
    }

    val event = new UserCreated()
    event.email = email
    event.password = password
    event.username = username
    UserCreated.query.create(event)

    return "Ok"
  }

  def login(email: String, password: String): String = {
    val account = AccountsByEmail.query.get(email)
    if (account.isEmpty) {
      return "Account not exist"
    }

    if (account.get.password != password) {
      return "Wrong password"
    }

    val info = AccountsById.query.get(account.get.owner)
    if (info.isEmpty) {
      return "Account not exist"
    } else {
      val lastLoginTime = info.get.lastLoginTime

      val event = new UserLoggedIn()
      event.owner = info.get.owner
      UserCreated.query.create(event)

      return "Ok. Last login time: $lastLoginTime"
    }
  }

  def changePassword(id: UUID, password: String, newPassword: String): String = {
    val account = AccountsById.query.get(id)
    if (account.isEmpty) {
      return "Account not exist"
    }

    if (account.get.password != password) {
      return "Wrong password"
    }

    account.get.password = newPassword
    AccountsById.query.update(account.get)

    val event = new PasswordChanged()
    event.password = password
    event.newPassword = newPassword
    PasswordChanged.query.create(event)

    return "Ok"
  }

  def logout(id: UUID): String = {
    val account = AccountsById.query.get(id)
    if (account.isEmpty) {
      return "Account not exist"
    }

    val event = new UserLoggedOut()
    event.owner = id
    UserLoggedOut.query.create(event)

    return "Ok"

  }
}