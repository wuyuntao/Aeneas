package com.wuyuntao.aeneas.tests.snapshots

import com.wuyuntao.aeneas.Snapshot
import com.wuyuntao.aeneas.QuerySet

object AccountsByUsername {
  val query = new QuerySet[AccountsByUsername]()
}

class AccountsByUsername extends Snapshot {
  column[String]("username")
}