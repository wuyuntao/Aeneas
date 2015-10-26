package com.wuyuntao.aeneas.tests.views

import java.util.UUID

import com.wuyuntao.aeneas.View

case class UserByEmail(val email: String, val id: UUID, val password: String) extends View