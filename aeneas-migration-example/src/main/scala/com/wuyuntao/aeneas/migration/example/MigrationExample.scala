package com.wuyuntao.aeneas.migration.example

import com.typesafe.config.ConfigFactory
import com.wuyuntao.aeneas.migration.Migrator

object MigrationExample extends App {
  val config = ConfigFactory.load();
  
  val migrator = Migrator(config.getConfig("aeneas.migration"))
  migrator.migrate()
}