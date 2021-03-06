package com.wuyuntao.aeneas.migration.example.migrations

import com.wuyuntao.aeneas.migration.Migration
import com.wuyuntao.aeneas.migration.dsl.DbModifier

class CreateUserLoggedOutEventTable extends Migration {
  def version = 20151030184903172L

  def up(db: DbModifier) = {
    db.executeSql("""CREATE TABLE user_logged_out_events (
      |  event_id timeuuid PRIMARY KEY,
      |  event_version int,
      |)
      |""".stripMargin)
  }

  def down(db: DbModifier) = {
    db.executeSql("DROP TABLE user_logged_out_events")
  }
}
