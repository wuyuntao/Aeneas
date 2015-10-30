package com.wuyuntao.aeneas.dsl

import com.wuyuntao.aeneas.util.Text

abstract class UserDefinedType {
  def name = Text.underscore(getClass.getSimpleName)
}