package com.wuyuntao.aeneas.migration

import java.util.Date

import com.datastax.driver.core.Row
import com.wuyuntao.aeneas.migration.dsl.DbModifier

trait Migration {
  def version: Long
  def up(db: DbModifier)
  def down(db: DbModifier)
}

final case class MigrationInfo private (version: Long, name: String, timestamp: Date) {
  private[migration] def this(migration: Migration) = {
    this(migration.version, migration.getClass.getSimpleName, new Date())
  }

  private[migration] def this(row: Row) = {
    this(row.getLong("version"), row.getString("name"), row.getDate("timestamp"))
  }
}