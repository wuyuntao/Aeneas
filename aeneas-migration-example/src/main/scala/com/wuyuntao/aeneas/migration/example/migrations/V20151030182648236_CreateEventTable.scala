package com.wuyuntao.aeneas.migration.example.migrations

import com.wuyuntao.aeneas.migration.Migration
import com.wuyuntao.aeneas.migration.dsl.DbModifier

class CreateEventTable extends Migration {
  def version = 20151030182648236L

  def up(db: DbModifier) = {
    db.executeSql("""CREATE TABLE events (
      |  entity_id timeuuid,
      |  partition_nr bigint,
      |  sequence_nr bigint,
      |  event_type text,
      |  event_id timeuuid,
      |  timestamp timestamp,
      |  PRIMARY KEY ((entity_id, partition_nr), sequence_nr)
      |) WITH CLUSTERING ORDER BY (sequence_nr ASC)
      |""".stripMargin)
  }
  
  def down(db: DbModifier) = {
    db.executeSql("DROP TABLE events")
  }
}