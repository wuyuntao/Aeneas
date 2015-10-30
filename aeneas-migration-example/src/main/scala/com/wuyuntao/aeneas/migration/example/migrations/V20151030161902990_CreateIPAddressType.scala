package com.wuyuntao.aeneas.migration.example.migrations

import com.wuyuntao.aeneas.migration.Migration
import com.wuyuntao.aeneas.migration.dsl.DbModifier

class CreateIPAddressType extends Migration {
  def version = 20151030161902990L

  def up(db: DbModifier) = {
    db.executeSql("""CREATE TYPE ip_address (
      |  ip text,
      |  port int,
      |)""".stripMargin)
  }

  def down(db: DbModifier) = {
    db.executeSql("DROP TYPE ip_address")
  }
}
