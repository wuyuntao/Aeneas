package com.wuyuntao.aeneas.migration.example.migrations

import com.wuyuntao.aeneas.migration.Migration
import com.wuyuntao.aeneas.migration.dsl.DbModifier

class CreateSnapshotTable extends Migration {
  def version = 20151030182715953L

  def up(db: DbModifier) = {
    db.executeSql("""CREATE TABLE snapshots (
      |  entity_id timeuuid,
      |  partition_nr bigint,
      |  sequence_nr bigint,
      |  snapshot_type text,
      |  snapshot_id timeuuid,
      |  timestamp timestamp,
      |  PRIMARY KEY ((entity_id, partition_nr), sequence_nr)
      |) WITH CLUSTERING ORDER BY (sequence_nr ASC)
      |""".stripMargin)
  }

  def down(db: DbModifier) = {
    db.executeSql("DROP TABLE snapshots")
  }
}
