package com.wuyuntao.aeneas.migration.example.migrations

import com.wuyuntao.aeneas.migration.Migration
import com.wuyuntao.aeneas.migration.dsl.DbModifier

class CreateUserCreatedEventTable extends Migration {
  def version = 20151030184832555L

  def up(db: DbModifier) = {
    db.executeSql("""CREATE TABLE user_created_events (
      |  event_id timeuuid PRIMARY KEY,
      |  event_version int,
      |  email text,
      |  password text,
      |  username text
      |)
      |""".stripMargin)
  }

  def down(db: DbModifier) = {
    db.executeSql("DROP TABLE user_created_events")
  }
}
