package com.wuyuntao.aeneas.tests.snapshots

import com.wuyuntao.aeneas.QuerySet
import com.wuyuntao.aeneas.Snapshot

object AccountByEmail {
  val query = new QuerySet[AccountByEmail]()
}

class AccountByEmail extends Snapshot {
  column[String]("email")
  column[String]("password")
}