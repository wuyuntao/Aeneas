package com.wuyuntao.aeneas.migration

import java.util.Date

import scala.collection.JavaConversions.asScalaBuffer
import scala.collection.JavaConversions.asScalaSet
import scala.collection.mutable.HashMap

import org.reflections.Reflections
import org.slf4j.LoggerFactory

import com.datastax.driver.core.Cluster
import com.datastax.driver.core.querybuilder.QueryBuilder
import com.typesafe.config.Config

object Migrator {
  def apply(config: Config): Migrator = new Migrator(config)
}

class Migrator private[migration] (private val config: Config) {
  private lazy val logger = LoggerFactory.getLogger(getClass)

  private lazy val session = {
    val builder = Cluster.builder

    config.getStringList("database.cassandra.contact-points").foreach { builder.addContactPoint }

    val cluster = builder.build
    cluster.connect
  }

  def migrate() = {
    createKeyspaceAndMigrationsTable

    val versions = getSortedAppliedMigrationVersions.map { _.version }
    val migrations = getDefinedMigrations

    for (migration <- migrations if !versions.contains(migration.version)) {
      val batch = new Batch(session)
      migration.up(batch)
      addVersion(migration)
    }
  }

  def migrateTo(version: Long) = {
    createKeyspaceAndMigrationsTable

    val versions = getSortedAppliedMigrationVersions.map { _.version }
    val migrations = getDefinedMigrations

    for (migration <- migrations) {
      if (migration.version <= version) {
        if (!versions.contains(migration.version)) {
          val batch = new Batch(session)
          migration.up(batch)
          addVersion(migration)
        }
      } else {
        if (versions.contains(migration.version)) {
          val batch = new Batch(session)
          migration.down(batch)
          removeVersion(migration)
        }
      }
    }
  }

  def list = {
    getDefinedMigrations.map { m => new MigrationInfo(m) }
  }

  def listDb = {
    getSortedAppliedMigrationVersions
  }

  def close = {
    session.close
    session.getCluster.close
  }

  private def createKeyspaceAndMigrationsTable = {
    val keyspace = config.getString("database.cassandra.keyspace")
    val table = config.getString("migration.table")

    session.execute(s"""CREATE KEYSPACE IF NOT EXISTS ${keyspace}
      |  WITH REPLICATION = {
      |    'class': 'SimpleStrategy',
      |    'replication_factor': 1
      |  }
      |""".stripMargin)

    session.execute(s"Use ${keyspace}")

    session.execute(s"""CREATE TABLE IF NOT EXISTS ${keyspace}.${table} (
      |    version bigint PRIMARY KEY,
      |    name text,
      |    timestamp timestamp
      |  )
      |""".stripMargin)
  }

  private def getDefinedMigrations = {
    val migrations = new HashMap[Long, Migration]()
    val packages = config.getStringList("dsl.packages")

    for (p <- packages) {
      val reflections = new Reflections(p)
      val classes = reflections.getSubTypesOf(classOf[Migration])

      for (c <- classes) {
        val migration = c.getDeclaredConstructors()(0).newInstance().asInstanceOf[Migration]

        if (!migrations.contains(migration.version))
          migrations.put(migration.version, migration)
        else
          throw new IllegalArgumentException(s"Duplicate migration version ${migration.version}")
      }
    }

    migrations.values.toSeq.sortWith(_.version < _.version)
  }

  private def getSortedAppliedMigrationVersions: Seq[MigrationInfo] = {
    val keyspace = config.getString("database.cassandra.keyspace")
    val table = config.getString("migration.table")

    val cql = s"""SELECT version, name, timestamp
      |  FROM ${keyspace}.${table}
      |""".stripMargin
    session.execute(cql).all.map { new MigrationInfo(_) }.sortWith { _.version < _.version }.toSeq
  }

  private def addVersion(migration: Migration) = {
    val keyspace = config.getString("database.cassandra.keyspace")
    val table = config.getString("migration.table")

    val statement = QueryBuilder.insertInto(keyspace, table).
      value("version", migration.version).
      value("name", migration.getClass.getSimpleName).
      value("timestamp", new Date)

    logger.debug(s"Migration ${migration.version} - ${migration.getClass.getName} added")

    session.execute(statement)
  }

  private def removeVersion(migration: Migration) = {
    val keyspace = config.getString("database.cassandra.keyspace")
    val table = config.getString("migration.table")

    val cql = s"""DELETE FROM ${keyspace}.${table}
      |  WHERE version = ${migration.version}
      |""".stripMargin

    logger.debug(s"Migration ${migration.version} - ${migration.getClass.getName} removed")

    session.execute(cql)
  }
}