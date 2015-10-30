package com.wuyuntao.aeneas.migration

import com.typesafe.config.ConfigFactory
import scopt.OptionParser
import com.wuyuntao.aeneas.migration.codegen.MigrationGen

trait MigratorApp extends App {

  private object Command {
    val Migrate = "migrate"
    val List = "list"
    val ListDb = "listdb"
    val New = "new"
  }

  private case class Config(command: String = null,
                            version: Long = -1L,
                            name: String = null,
                            outputDirectory: String = null)

  protected def run(args: Array[String]) = {
    val app = "aeneas-migrator" // TODO get app name and version from sbt  

    val parser = new OptionParser[Config](app) {
      head(app, "0.0.1")

      help("help").
        text("Print this usage text")

      cmd(Command.Migrate).
        action { (o, c) => c.copy(command = Command.Migrate) }.
        children(
          opt[Long]('v', "version").
            action { (o, c) => c.copy(version = o) })

      cmd(Command.List).
        action { (o, c) => c.copy(command = Command.List) }

      cmd(Command.ListDb).
        action { (o, c) => c.copy(command = Command.ListDb) }

      cmd(Command.New).
        action { (o, c) => c.copy(command = Command.New) }.
        children(
          opt[String]('n', "name").
            required().
            action { (o, c) => c.copy(name = o) },
          opt[String]('o', "output-directory").
            required().
            action { (o, c) => c.copy(outputDirectory = o) })
    }

    parser.parse(args, Config()) match {
      case Some(c) =>
        val config = ConfigFactory.load().getConfig("aeneas")

        c.command match {
          case Command.Migrate =>
            val migrator = Migrator(config)
            if (c.version < 0) {
              migrator.migrate()
            } else {
              migrator.migrateTo(c.version)
            }
            migrator.close

          case Command.List =>
            val migrator = Migrator(config)
            val applied = migrator.listDb

            migrator.list.foreach { m =>
              val timestamp = applied.find { c => c.version == m.version } match {
                case Some(t) => s"- ${t.timestamp}"
                case None    => "- NEW"
              }

              println(s"${m.version} - ${m.name} ${timestamp}")
            }
            migrator.close

          case Command.ListDb =>
            val migrator = Migrator(config)
            migrator.listDb.foreach { m =>
              println(s"${m.version} - ${m.name} - ${m.timestamp}")
            }
            migrator.close

          case Command.New =>
            MigrationGen.generate(config, c.name, c.outputDirectory)

          case _ =>
            parser.showUsage
        }

      case None =>
    }
  }
}