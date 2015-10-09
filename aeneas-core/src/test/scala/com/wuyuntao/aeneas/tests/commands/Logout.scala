package com.wuyuntao.aeneas.tests.commands

import java.util.UUID

import com.wuyuntao.aeneas.Command

class Logout(val id: UUID, val email: String, val password: String) extends Command {}