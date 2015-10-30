package com.wuyuntao.aeneas.migration.codegen

import java.io.File
import java.io.PrintWriter
import java.nio.file.Paths
import java.text.SimpleDateFormat
import java.util.Date

import com.typesafe.config.Config

object MigrationGen {
  private val versionFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS")

  def generate(config: Config, name: String, outputDirectory: String) = {
    val time = new Date()
    val version = versionFormat.format(time).toLong
    val packageName = config.getString("migration.package")

    val body = s"""package ${packageName}
      |
      |import com.wuyuntao.aeneas.migration.Migration
      |import com.wuyuntao.aeneas.migration.dsl.DbModifier
      |
      |class ${name} extends Migration {
      |  def version = ${version}L
      |
      |  def up(db: DbModifier) = {
      |  }
      |  
      |  def down(db: DbModifier) = {
      |  }
      |}
      |""".stripMargin

    val filename = Paths.get(outputDirectory, s"V${version}_${name}.scala")
    val writer = new PrintWriter(new File(filename.toString))
    writer.write(body)
    writer.close()

    println(s"Migration ${version} - ${name} created")
  }
}