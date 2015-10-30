package com.wuyuntao.aeneas.migration.example.migrations

import com.wuyuntao.aeneas.migration.Migration
import com.wuyuntao.aeneas.migration.dsl.DbModifier

class CreateUserSnapshotTable extends Migration {
  def version = 20151030184947118L

  def up(db: DbModifier) = {
    db.executeSql("""CREATE TABLE user_snapshots (
      |  snapshot_id timeuuid PRIMARY KEY,
      |  snapshot_version int,
      |  email text,
      |  password text,
      |  username text
      |)
      |""".stripMargin)
  }

  def down(db: DbModifier) = {
    db.executeSql("DROP TABLE user_snapshots")
  }
}
