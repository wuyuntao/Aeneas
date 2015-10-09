package com.wuyuntao.aeneas.tests.commands

import java.util.UUID

import com.wuyuntao.aeneas.Command

class ChangePassword(val id: UUID, val password: String, val newPassword: String) extends Command {}