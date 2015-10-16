package com.wuyuntao.aeneas.dsl.codegen

object CodeGenApp extends App {
  Config(args) match {
    case Some(config) =>
      // Do stuff here

    case None =>
      println("exit")
  }
}