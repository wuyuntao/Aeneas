package com.wuyuntao.aeneas.migration

import org.slf4j.LoggerFactory

import com.datastax.driver.core.RegularStatement
import com.datastax.driver.core.Session
import com.wuyuntao.aeneas.migration.dsl.DbModifier

private class Batch(private val session: Session) extends DbModifier {
  private lazy val logger = LoggerFactory.getLogger(getClass)
  
  def executeStatement(statement: RegularStatement) = {
    logger.debug("Execute statement {}", statement.getQueryString)
    
    session.execute(statement)
  }
}