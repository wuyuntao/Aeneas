package com.wuyuntao.aeneas.dsl.codegen

import org.slf4j.LoggerFactory

import com.typesafe.config.Config

private final class MigrationGenerator(config: Config) {
  private lazy val logger = LoggerFactory.getLogger(getClass)

  private val lastTimestamp = 0L;

  def generate() = {
    generateForTypes
  }

  private def generateForTypes() = {
    // find types defined
    // foreach check type if exists
    // create type if not exists
    // check for missing field
    // add fields if not exist
  }
}