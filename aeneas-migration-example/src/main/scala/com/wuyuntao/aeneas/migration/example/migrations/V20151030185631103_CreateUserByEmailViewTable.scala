package com.wuyuntao.aeneas.migration.example.migrations

import com.wuyuntao.aeneas.migration.Migration
import com.wuyuntao.aeneas.migration.dsl.DbModifier

class CreateUserByEmailViewTable extends Migration {
  def version = 20151030185631103L

  def up(db: DbModifier) = {
    db.executeSql("""CREATE TABLE user_by_email_views (
      |  email text PRIMARY KEY,
      |  id timeuuid
      |)
      |""".stripMargin)
  }

  def down(db: DbModifier) = {
    db.executeSql("DROP TABLE user_by_email_views")
  }
}
