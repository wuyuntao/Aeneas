package com.wuyuntao.aeneas.migration.dsl

import com.datastax.driver.core.RegularStatement

trait ColumnModifier {
  private[dsl] def executeStatement(statement: RegularStatement)

  def createColumn = {

  }

  def dropColumn = {

  }
}