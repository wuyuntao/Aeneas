package com.wuyuntao.aeneas.tests.snapshots

import java.time.OffsetDateTime

import com.wuyuntao.aeneas.QuerySet
import com.wuyuntao.aeneas.Snapshot

/**
 * @author Wu Yuntao
 */

object AccountById {
  val query = new QuerySet[AccountById]()
}

class AccountById extends Snapshot {
  column[String]("username")
  column[String]("password")
  column[OffsetDateTime]("last_login_time")
}