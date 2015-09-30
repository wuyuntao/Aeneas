package com.wuyuntao.aeneas.tests.events

import com.wuyuntao.aeneas.Event

/**
 * @author Wu Yuntao
 */
class PasswordChanged extends Event {
  column[String]("newPassword")
}