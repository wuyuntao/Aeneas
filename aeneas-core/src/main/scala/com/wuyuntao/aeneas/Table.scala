package com.wuyuntao.aeneas

import scala.collection.mutable.HashMap

/**
 * @author Wu Yuntao
 */
trait Table {
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
}