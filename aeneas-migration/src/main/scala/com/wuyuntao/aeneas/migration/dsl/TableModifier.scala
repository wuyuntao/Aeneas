package com.wuyuntao.aeneas.migration.dsl

import com.datastax.driver.core.RegularStatement

trait TableModifier {
  def executeStatement(statement: RegularStatement)

  def createTable() = {

  }

  def dropTable() = {

  }
}