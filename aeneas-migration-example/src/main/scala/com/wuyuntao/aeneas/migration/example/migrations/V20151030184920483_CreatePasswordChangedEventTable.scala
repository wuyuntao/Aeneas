package com.wuyuntao.aeneas.migration.example.migrations

import com.wuyuntao.aeneas.migration.Migration
import com.wuyuntao.aeneas.migration.dsl.DbModifier

class CreatePasswordChangedEventTable extends Migration {
  def version = 20151030184920483L

  def up(db: DbModifier) = {
    db.executeSql("""CREATE TABLE password_changed_events (
      |  event_id timeuuid PRIMARY KEY,
      |  event_version int,
      |  new_password text
      |)
      |""".stripMargin)
  }

  def down(db: DbModifier) = {
    db.executeSql("DROP TABLE password_changed_events")
  }
}
