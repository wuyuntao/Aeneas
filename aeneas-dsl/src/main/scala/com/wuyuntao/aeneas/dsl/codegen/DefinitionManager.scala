package com.wuyuntao.aeneas.dsl.codegen

import scala.collection.JavaConversions.asScalaBuffer
import scala.collection.JavaConversions.asScalaSet
import scala.collection.mutable.HashMap

import org.reflections.Reflections

import com.typesafe.config.Config
import com.wuyuntao.aeneas.dsl.Command
import com.wuyuntao.aeneas.dsl.Event
import com.wuyuntao.aeneas.dsl.Query
import com.wuyuntao.aeneas.dsl.Snapshot
import com.wuyuntao.aeneas.dsl.UserDefinedType
import com.wuyuntao.aeneas.dsl.View

private object DefinitionManager {
  def apply(config: Config) = {
    val types = new HashMap[String, UserDefinedType]()
    val commands = new HashMap[String, Command]()
    val queries = new HashMap[String, Query]()
    val events = new HashMap[String, Event]()
    val snapshots = new HashMap[String, Snapshot]()
    val views = new HashMap[String, View]()

    val packages = config.getStringList("packages")

    // TODO Improve this later with ClassTag: http://docs.scala-lang.org/overviews/reflection/typetags-manifests.html
    for (p <- packages) {
      val reflections = new Reflections(p)
      
      reflections.getSubTypesOf(classOf[UserDefinedType]).foreach { cls =>
        val instance = cls.getDeclaredConstructors()(0).newInstance().asInstanceOf[UserDefinedType]
        types.put(cls.getName, instance)
      }

      reflections.getSubTypesOf(classOf[Command]).foreach { cls =>
        val instance = cls.getDeclaredConstructors()(0).newInstance().asInstanceOf[Command]
        commands.put(cls.getName, instance)
      }

      reflections.getSubTypesOf(classOf[Query]).foreach { cls =>
        val instance = cls.getDeclaredConstructors()(0).newInstance().asInstanceOf[Query]
        queries.put(cls.getName, instance)
      }

      reflections.getSubTypesOf(classOf[Event]).foreach { cls =>
        val instance = cls.getDeclaredConstructors()(0).newInstance().asInstanceOf[Event]
        events.put(cls.getName, instance)
      }

      reflections.getSubTypesOf(classOf[Snapshot]).foreach { cls =>
        val instance = cls.getDeclaredConstructors()(0).newInstance().asInstanceOf[Snapshot]
        snapshots.put(cls.getName, instance)
      }

      reflections.getSubTypesOf(classOf[View]).foreach { cls =>
        val instance = cls.getDeclaredConstructors()(0).newInstance().asInstanceOf[View]
        views.put(cls.getName, instance)
      }
    }
    
    new DefinitionManager(types.toMap, commands.toMap, queries.toMap, events.toMap, snapshots.toMap, views.toMap)
  }
}

private case class DefinitionManager(
  val types: Map[String, UserDefinedType],
  val commands: Map[String, Command],
  val queries: Map[String, Query],
  val events: Map[String, Event],
  val snapshots: Map[String, Snapshot],
  val views: Map[String, View])
