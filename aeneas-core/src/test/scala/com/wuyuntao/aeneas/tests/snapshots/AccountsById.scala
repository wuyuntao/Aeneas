package com.wuyuntao.aeneas.tests.snapshots

import java.time.OffsetDateTime

import com.wuyuntao.aeneas.QuerySet
import com.wuyuntao.aeneas.Snapshot

/**
 * @author Wu Yuntao
 */

object AccountsById {
  val query = new QuerySet[AccountsById]()
}

class AccountsById extends Snapshot {
  column[String]("username")
  column[String]("password")
  column[OffsetDateTime]("last_login_time")
}