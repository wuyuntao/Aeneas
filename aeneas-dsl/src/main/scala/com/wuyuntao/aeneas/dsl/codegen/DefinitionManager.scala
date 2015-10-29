package com.wuyuntao.aeneas.dsl.codegen

import scala.collection.mutable.HashSet
import com.typesafe.config.Config
import com.wuyuntao.aeneas.dsl._
import org.reflections.Reflections
import scala.collection.JavaConversions._

private final class DefinitionManager(config: Config) {
  val types = new HashSet[Type]()
  val commands = new HashSet[Command]()
  val queries = new HashSet[Query]()
  val events = new HashSet[Event]()
  val snapshots = new HashSet[Snapshot]()
  val views = new HashSet[View]()

  val packages = config.getStringList("packages")

  for (p <- packages) {
    val reflections = new Reflections(p)

    // TODO find def classes
  }
}