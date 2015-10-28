package com.wuyuntao.aeneas.migration.example.migrations

import com.wuyuntao.aeneas.migration.Migration
import com.wuyuntao.aeneas.migration.dsl.DbModifier

class DeleteCustomType extends Migration {
  def version = 20151028201606653L

  def up(db: DbModifier) = {
    db.executeSql("DROP TYPE fullname")
  }

  def down(db: DbModifier) = {
    db.executeSql("""CREATE TYPE fullname (
      |  first_name text,
      |  middle_name text,
      |  last_name text
      |)""".stripMargin)
  }
}