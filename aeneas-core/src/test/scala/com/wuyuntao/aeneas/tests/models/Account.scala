package com.wuyuntao.aeneas.tests.models

import com.wuyuntao.aeneas.Event
import com.wuyuntao.aeneas.Model
import com.wuyuntao.aeneas.tests.events.PasswordChanged
import com.wuyuntao.aeneas.tests.events.UserCreated
import com.wuyuntao.aeneas.tests.events.UserLoggedIn
import com.wuyuntao.aeneas.tests.events.UserLoggedOut

/**
 * @author Wu Yuntao
 */
object Account extends Model {

  // event handlers
  def onSave(event: Event): Unit = event match {
    case e: UserCreated     => onSaveUserCreated(e)
    case e: UserLoggedIn    => onSaveUserLoggedIn(e)
    case e: UserLoggedOut   => onSaveUserLoggedOut(e)
    case e: PasswordChanged => onSavePasswordChanged(e)
  }

  def onSaveUserCreated(event: UserCreated): Unit = {
    // Create all snapshots of account 
  }

  def onSaveUserLoggedIn(event: UserLoggedIn): Unit = {
    // Update last login time field of AccountsById
  }

  def onSaveUserLoggedOut(event: UserLoggedOut): Unit = {
    // Do nothing
  }

  def onSavePasswordChanged(event: PasswordChanged): Unit = {
    // Update password fields in snapshots
  }
}