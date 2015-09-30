package com.wuyuntao.aeneas.tests.snapshots

import java.time.OffsetDateTime
import com.wuyuntao.aeneas.Snapshot

/**
 * @author Wu Yuntao
 */
class AccountDashboard extends Snapshot {
  column[String]("username")
  column[String]("password_hash")
  column[OffsetDateTime]("last_login_time")
}