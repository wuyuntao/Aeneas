package com.wuyuntao.aeneas.migration.dsl

import com.datastax.driver.core.RegularStatement
import com.datastax.driver.core.SimpleStatement

trait DbModifier extends TableModifier with ColumnModifier with TypeModifier {
  def executeStatement(statement: RegularStatement)

  def executeSql(sql: String) = {
    executeStatement(new SimpleStatement(sql))
  }
}