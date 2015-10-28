package com.wuyuntao.aeneas.migration.dsl

import com.datastax.driver.core.RegularStatement

trait TypeModifier {
  private[dsl] def executeStatement(statement: RegularStatement)

  def createType = {

  }

  def dropType = {

  }
}