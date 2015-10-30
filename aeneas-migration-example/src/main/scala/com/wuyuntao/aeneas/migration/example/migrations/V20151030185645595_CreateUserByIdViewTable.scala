package com.wuyuntao.aeneas.migration.example.migrations

import com.wuyuntao.aeneas.migration.Migration
import com.wuyuntao.aeneas.migration.dsl.DbModifier

class CreateUserByIdViewTable extends Migration {
  def version = 20151030185645595L

  def up(db: DbModifier) = {
    db.executeSql("""CREATE TABLE user_by_id_views (
      |  id timeuuid PRIMARY KEY,
      |  email text,
      |  username text,
      |  last_login_time timestamp
      |)
      |""".stripMargin)    
  }
  
  def down(db: DbModifier) = {
    db.executeSql("DROP TABLE user_by_id_views")
  }
}
