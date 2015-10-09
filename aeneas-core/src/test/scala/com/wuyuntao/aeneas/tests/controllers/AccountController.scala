package com.wuyuntao.aeneas.tests.controllers

import java.util.UUID
import com.datastax.driver.core.utils.UUIDs
import com.wuyuntao.aeneas.tests.commands._
import com.wuyuntao.aeneas.tests.events._
import com.wuyuntao.aeneas.tests.models._
import com.wuyuntao.aeneas.tests.snapshots._

class AccountController {

  private val account = new Account

  def register(command: Register) = {
    account.process(command)
  }

  def login(command: Login) = {
    account.process(command)
  }

  def changePassword(command: ChangePassword) = {
    account.process(command)
  }

  def logout(command: Logout) = {
    account.process(command)
  }
}