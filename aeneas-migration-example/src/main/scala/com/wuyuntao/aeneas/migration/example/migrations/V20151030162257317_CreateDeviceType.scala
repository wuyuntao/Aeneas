package com.wuyuntao.aeneas.migration.example.migrations

import com.wuyuntao.aeneas.migration.Migration
import com.wuyuntao.aeneas.migration.dsl.DbModifier

class CreateDeviceType extends Migration {
  def version = 20151030162257317L

  def up(db: DbModifier) = {
    db.executeSql("""CREATE TYPE device_alias (
      |  platform text,
      |  version text,
      |  device_identifier text,
      |  device_description text
      |)""".stripMargin)
  }

  def down(db: DbModifier) = {
    db.executeSql("DROP TYPE device_alias")
  }
}
