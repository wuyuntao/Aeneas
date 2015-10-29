package com.wuyuntao.aeneas.cli

import com.typesafe.config.ConfigFactory
import com.wuyuntao.aeneas.migration.Migrator

import scopt.OptionParser

object CliApp extends App {

  val parser = new OptionParser[CliConfig]("aeneas-cli") {
    head("aeneas-cli", "0.0.1")

    help("help")
      .text("Print usage text")

    cmd("migrate").
      action { (o, c) => c.copy(migrate = new MigrateConfig()) }.
      children(
        opt[String]('i', "input-jar-path")
          .action { (o, c) => c.copy(migrate = c.migrate.copy(inputJarPath = o)) },
        opt[Long]('v', "migrate-to-version")
          .action { (o, c) => c.copy(migrate = c.migrate.copy(version = o)) })
  }

  parser.parse(args, CliConfig()) match {
    case Some(config) =>
      if (config.migrate != null) {
        migrate(config.migrate)
      }

    case None =>
  }

  def migrate(config: MigrateConfig) = {
    val config = ConfigFactory.load();

    val migrator = Migrator(config.getConfig("aeneas.migration"))
    migrator.migrate()
  }
}