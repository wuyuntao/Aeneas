package com.wuyuntao.aeneas.migration

import com.typesafe.config.ConfigFactory

import scopt.OptionParser

trait MigratorApp extends App {

  private object Command {
    val Migrate = "migrate"
    val List = "list"
    val ListDb = "listdb"
  }

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
    }

    val config = ConfigFactory.load()
    val migrator = Migrator(config.getConfig("aeneas"))

    parser.parse(args, Config()) match {
      case Some(c) =>
        c.command match {
          case Command.Migrate =>
            if (c.version < 0) {
              migrator.migrate()
            } else {
              migrator.migrateTo(c.version)
            }

          case Command.List =>
            val applied = migrator.listDb

            migrator.list.foreach { m =>
              val timestamp = applied.find { c => c.version == m.version } match {
                case Some(t) => s"- ${t.timestamp}"
                case None    => "- NEW"
              }

              println(s"${m.version} - ${m.name} ${timestamp}")
            }

          case Command.ListDb =>
            migrator.listDb.foreach { m =>
              println(s"${m.version} - ${m.name} - ${m.timestamp}")
            }

          case _ =>
            parser.showUsage
        }

      case None =>
    }

    migrator.close
  }

  private case class Config(command: String = null, version: Long = -1L)
}