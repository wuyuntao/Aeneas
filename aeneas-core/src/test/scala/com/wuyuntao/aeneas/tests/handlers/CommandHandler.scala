package com.wuyuntao.aeneas.tests.handlers

import java.time.OffsetDateTime

import com.datastax.driver.core.utils.UUIDs
import com.wuyuntao.aeneas.Command
import com.wuyuntao.aeneas.Event
import com.wuyuntao.aeneas.Snapshot
import com.wuyuntao.aeneas.tests.commands._
import com.wuyuntao.aeneas.tests.events._
import com.wuyuntao.aeneas.tests.snapshots._

class CommandHandler {
  var user: User = null
  
  def persist(event: Event) = {
    // persist event
  }
  
  def persist(snapshot: Snapshot) = {
    // persist snapshot
  }

  def process(command: Command) = command match {
    case c: Register       => register(c)
    case c: Login          => login(c)
    case c: Logout         => logout(c)
    case c: ChangePassword => changePassword(c)
  }

  def register(command: Register) = {
	  user = new User(UUIDs.timeBased, 1, command.email, command.password, command.username)
    val event = new UserCreated(user.owner, user.revision, OffsetDateTime.now, user.email, user.password, user.username, command.gender)
    
    persist(event)
    persist(user)
  }

  def login(command: Login) = {
    user = user.copy(revision = user.revision + 1)
    val event = new UserLoggedIn(user.owner, user.revision, OffsetDateTime.now)
    
    persist(event)
    persist(user)
  }

  def logout(command: Logout) = {
    user = user.copy(revision = user.revision + 1)
    val event = new UserLoggedOut(user.owner, user.revision, OffsetDateTime.now)
    
    persist(event)
    persist(user)
  }

  def changePassword(command: ChangePassword) = {
    user = user.copy(revision = user.revision + 1, password = command.newPassword)
    val event = new PasswordChanged(user.owner, user.revision, OffsetDateTime.now, user.password)
    
    persist(event)
    persist(user)
  }
}