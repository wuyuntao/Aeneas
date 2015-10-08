package com.wuyuntao.aeneas.tests.snapshots

import com.wuyuntao.aeneas.QuerySet
import com.wuyuntao.aeneas.Snapshot

object AccountsByEmail {
  val query = new QuerySet[AccountsByEmail]()
}

class AccountsByEmail extends Snapshot {
  column[String]("email")
  column[String]("password")
}