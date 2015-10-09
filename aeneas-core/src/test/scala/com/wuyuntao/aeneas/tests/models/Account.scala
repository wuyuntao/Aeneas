package com.wuyuntao.aeneas.tests.models

import com.datastax.driver.core.utils.UUIDs
import com.wuyuntao.aeneas._
import com.wuyuntao.aeneas.tests.commands._
import com.wuyuntao.aeneas.tests.events._
import com.wuyuntao.aeneas.tests.snapshots._

/**
 * @author Wu Yuntao
 */
class Account extends Model {
  def process(command: Command): Option[Event] = command match {
    case c: Register       => register(c)
    case c: Login          => login(c)
    case c: Logout         => logout(c)
    case c: ChangePassword => changePassword(c)
    case _                 => None
  }

  def register(command: Register): Option[Event] = {
    val emailExists = AccountByEmail.query.exists(command.email)
    if (emailExists) {
      return None
    }

    val usernameExists = AccountByUsername.query.exists(command.username)
    if (usernameExists) {
      return None
    }

    val id = UUIDs.timeBased
    val event = new UserCreated()
    event.id = id
    event.email = command.email
    event.password = command.password
    event.username = command.username

    UserCreated.query.create(event)

    return Some(event)
  }

  def login(command: Login): Option[Event] = {
    val account = AccountByEmail.query.get(command.email)
    if (account.isEmpty) {
      return None
    }

    if (account.get.password != command.password) {
      return None
    }

    val info = AccountById.query.get(account.get.owner)
    if (info.isEmpty) {
      return None
    } else {
      val event = new UserLoggedIn()
      event.owner = info.get.owner

      UserLoggedIn.query.create(event)

      return Some(event)
    }
  }

  def logout(command: Logout): Option[Event] = {

    val account = AccountById.query.get(command.id)
    if (account.isEmpty) {
      return None
    }

    val event = new UserLoggedOut()
    event.owner = command.id

    UserLoggedOut.query.create(event)

    return Some(event)
  }

  def changePassword(command: ChangePassword): Option[Event] = {
    val account = AccountById.query.get(command.id)
    if (account.isEmpty) {
      return None
    }

    if (account.get.password != command.password) {
      return None
    }

    val event = new PasswordChanged()
    event.password = command.password
    event.newPassword = command.newPassword

    PasswordChanged.query.create(event)

    return Some(event)

  }
}