package com.wuyuntao.aeneas.tests.commands

import com.wuyuntao.aeneas.Command

class Register(val email: String, val password: String, val username: String) extends Command {}