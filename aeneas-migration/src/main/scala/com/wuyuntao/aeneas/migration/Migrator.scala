package com.wuyuntao.aeneas.migration

import java.time.OffsetDateTime

import scala.collection.JavaConversions.asScalaBuffer
import scala.reflect.runtime.{universe => ru}

import org.slf4j.LoggerFactory

import com.datastax.driver.core.Cluster
import com.datastax.driver.core.querybuilder.QueryBuilder
import com.typesafe.config.Config

import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner

object Migrator {
  def apply(config: Config): Migrator = new Migrator(config)
}

class Migrator private[migration] (private val config: Config) {
  private lazy val logger = LoggerFactory.getLogger(getClass)

  private lazy val session = {
    val builder = Cluster.builder

    config.getStringList("cassandra.contact-points").foreach { builder.addContactPoint }

    val cluster = builder.build
    cluster.connect
  }

  def migrate() = {
    createKeyspaceAndMigrationsTable

    val versions = getSortedAppliedMigrationVersions
    val migrations = getDefinedMigrations

    for (migration <- migrations if !versions.contains(migration.version)) {
      val batch = new Batch(session)
      migration.up(batch)
      addVersion(migration)
    }
    
    closeSession
  }

  def migrateTo(version: Long) = {
    createKeyspaceAndMigrationsTable

    val versions = getSortedAppliedMigrationVersions
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
    
    closeSession
  }

  private def createKeyspaceAndMigrationsTable = {
    val keyspace = config.getString("cassandra.keyspace")
    val table = config.getString("cassandra.table")

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
  
  private def closeSession = {
    session.close
    session.getCluster.close
  }

  private def getDefinedMigrations: Seq[Migration] = {
    val mirror = ru.runtimeMirror(getClass.getClassLoader)
    val scanner = new FastClasspathScanner().scan()
    val classes = scanner.getNamesOfClassesImplementing("com.wuyuntao.aeneas.migration.Migration")

    logger.debug("Found {} migrations", classes.size)
    
    scanner.getNamesOfAllClasses.filter { _.startsWith("com.wuyuntao") }.foreach { println }
    
    classes.map { c =>
      Class.forName(c).newInstance().asInstanceOf[Migration]
    }
  }

  private def getSortedAppliedMigrationVersions: Seq[Long] = {
    val keyspace = config.getString("cassandra.keyspace")
    val table = config.getString("cassandra.table")

    val cql = s"""SELECT version
      |  FROM ${keyspace}.${table}
      |""".stripMargin
    session.execute(cql).all.map { _.getLong("version") }.sorted.toSeq
  }

  private def addVersion(migration: Migration) = {
    val keyspace = config.getString("cassandra.keyspace")
    val table = config.getString("cassandra.table")

    val statement = QueryBuilder.insertInto(keyspace, table).
      value("version", migration.version).
      value("name", migration.getClass.getName).
      value("timestamp", OffsetDateTime.now)

    logger.debug(s"Migration ${migration.version} - ${migration.getClass.getName} added")

    session.execute(statement)
  }

  private def removeVersion(migration: Migration) = {
    val keyspace = config.getString("cassandra.keyspace")
    val table = config.getString("cassandra.table")

    val cql = s"""DELETE FROM ${keyspace}.${table}
      |  WHERE version = ${migration.version}
      |""".stripMargin

    logger.debug(s"Migration ${migration.version} - ${migration.getClass.getName} removed")

    session.execute(cql)
  }
}