package com.wuyuntao.aeneas.migration.example.migrations

import com.wuyuntao.aeneas.migration.Migration
import com.wuyuntao.aeneas.migration.dsl.DbModifier

class CreateUserInEmailViewTable extends Migration {
  def version = 20151030185631103L

  def up(db: DbModifier) = {
  }
  
  def down(db: DbModifier) = {
  }
}
