package com.wuyuntao.aeneas.dsl

import com.wuyuntao.aeneas.util.Text

abstract class Query extends Table {
  def name = "{}_queries".format(Text.underscore(getClass.getSimpleName))
}