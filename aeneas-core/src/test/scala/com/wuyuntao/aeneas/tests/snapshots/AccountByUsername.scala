package com.wuyuntao.aeneas.tests.snapshots

import com.wuyuntao.aeneas.Snapshot
import com.wuyuntao.aeneas.QuerySet

object AccountByUsername {
  val query = new QuerySet[AccountByUsername]()
}

class AccountByUsername extends Snapshot {
  column[String]("username")
}