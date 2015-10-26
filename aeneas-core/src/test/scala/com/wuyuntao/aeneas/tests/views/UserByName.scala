package com.wuyuntao.aeneas.tests.views

import java.util.UUID

import com.wuyuntao.aeneas.View

case class UserByName(val username: String, val id: UUID) extends View