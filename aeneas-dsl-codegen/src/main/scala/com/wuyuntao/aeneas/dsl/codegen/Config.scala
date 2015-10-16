package com.wuyuntao.aeneas.dsl.codegen

import scopt.OptionParser

object Config {
  def apply(args: Seq[String]): Option[Config] = {
    val parser = new OptionParser[Config]("aeneas-dsl-codegen") {
      head("aeneas-code-gen", "0.0.1")

      opt[String]('i', "input-jar-path").
        action { (i, c) => c.copy(inputJarPath = i) }

      opt[String]('o', "output-code-directory").
        action { (o, c) => c.copy(outputCodeDirectory = o) }
    }

    parser.parse(args, new Config()) 
  }
}

case class Config(
  inputJarPath: String = null,
  outputCodeDirectory: String = null)