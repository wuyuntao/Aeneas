package com.wuyuntao.aeneas.migration

import com.wuyuntao.aeneas.migration.dsl.DbModifier

trait Migration {
  def version: Long
  def up(db: DbModifier)
  def down(db: DbModifier)
}