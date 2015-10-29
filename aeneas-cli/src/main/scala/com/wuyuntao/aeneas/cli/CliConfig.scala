package com.wuyuntao.aeneas.cli

case class CliConfig(
  migrate: MigrateConfig = null)

case class MigrateConfig(
  inputJarPath: String = null,
  version: Long = Long.MaxValue)
