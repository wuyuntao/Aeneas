package com.wuyuntao.aeneas.migration.example.migrations

import com.wuyuntao.aeneas.migration.Migration
import com.wuyuntao.aeneas.migration.dsl.DbModifier

class CreateUserLoggedOutEventTable extends Migration {
  def version = 20151030184903172L

  def up(db: DbModifier) = {
  }
  
  def down(db: DbModifier) = {
  }
}