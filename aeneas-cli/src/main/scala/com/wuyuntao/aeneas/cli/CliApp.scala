package com.wuyuntao.aeneas.cli

object CliApp extends App {
  
  Config(args) match {
    case Some(config) =>
      // Do stuff here
      println("todo1")
      
    case None =>
  }
}