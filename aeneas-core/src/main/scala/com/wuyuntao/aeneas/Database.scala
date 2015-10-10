package com.wuyuntao.aeneas

import com.datastax.driver.core.Cluster
import com.datastax.driver.core.ResultSet

object Database {
  private lazy val cluster = {
    val builder = Cluster.builder

    Configuration.contactPoints.foreach { builder.addContactPoint }

    builder.build
  }

  private lazy val session = {
    cluster.connect(Configuration.keySpace)
  }

  def execute(cql: String): ResultSet = {
    session.execute(cql)
  }
}