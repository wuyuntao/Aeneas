package com.wuyuntao.aeneas.dsl

object Column {
  def apply[T](version: Int, name: String = null) = new Column[T](version, name)
}

class Column[T](val version: Int, val name: String = null)