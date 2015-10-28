package com.wuyuntao.aeneas

import scala.collection.JavaConversions.asScalaBuffer

import com.typesafe.config.ConfigFactory

object Configuration {
  private lazy val config = ConfigFactory.load();

  lazy val contactPoints = {
    if (config.hasPath("aeneas.database.cassandra.contact-points")) {
      config.getStringList("aeneas.database.cassandra.contact-points").toSeq
    } else {
      Seq("127.0.0.1")
    }
  }

  lazy val keySpace = {
    if (config.hasPath("aeneas.database.cassandra.keyspace")) {
      config.getString("aeneas.database.cassandra.keyspace")
    } else {
      "aeneas"
    }
  }
}