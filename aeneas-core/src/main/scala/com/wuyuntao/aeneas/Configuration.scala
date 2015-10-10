package com.wuyuntao.aeneas

import scala.collection.JavaConversions.asScalaBuffer

import com.typesafe.config.ConfigFactory

object Configuration {
  private lazy val config = ConfigFactory.load();

  lazy val contactPoints = {
    if (config.hasPath("aeneas.database.cassandra.contactPoints")) {
      config.getStringList("aeneas.database.cassandra.contactPoints").toSeq
    } else {
      Seq("127.0.0.1")
    }
  }

  lazy val keySpace = {
    if (config.hasPath("aeneas.database.cassandra.keySpace")) {
      config.getString("aeneas.database.cassandra.keySpace")
    } else {
      "aeneas"
    }
  }
}