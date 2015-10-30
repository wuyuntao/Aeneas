package com.wuyuntao.aeneas.dsl.codegen

import org.slf4j.LoggerFactory

import com.typesafe.config.Config

private abstract class Generator(protected val config: Config) {
  protected lazy val logger = LoggerFactory.getLogger(getClass)
  
  def generate()
}