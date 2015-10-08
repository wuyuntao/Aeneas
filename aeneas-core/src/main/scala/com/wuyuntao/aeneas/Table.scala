package com.wuyuntao.aeneas

import scala.collection.mutable.HashMap
import scala.language.dynamics

/**
 * @author Wu Yuntao
 */
trait Table extends Dynamic {
  private val columns = HashMap[String, Column]()

  def column[T](name: String): Column = {
    if (name == null || name.length == 0)
      throw new IllegalArgumentException("Missing column name")

    if (columns.contains(name))
      throw new IllegalArgumentException("Multiple column '$name' defined")

    val column = Column(name)
    columns.put(name, column)

    column
  }

  def updateDynamic(key: String)(value: Any): Unit = {
    val column = columns(key)
    if (column == null)
      throw new IllegalArgumentException("Invalid column name")
    
//    column.value = value
  }

  def selectDynamic(key: String): Any = {
    val column = columns.get(key)
    if (column == null)
      throw new IllegalArgumentException("Invalid column name")

    column
  }
}