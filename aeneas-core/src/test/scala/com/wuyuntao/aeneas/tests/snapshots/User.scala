package com.wuyuntao.aeneas.tests.snapshots

import java.util.UUID

import com.wuyuntao.aeneas.Snapshot

case class User(val owner: UUID,
                val revision: Int,
                val email: String,
                val password: String,
                val username: String)
  extends Snapshot