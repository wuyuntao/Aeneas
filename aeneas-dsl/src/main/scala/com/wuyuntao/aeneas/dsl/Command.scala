package com.wuyuntao.aeneas.dsl

import com.wuyuntao.aeneas.util.Text

abstract class Command extends Table {
  def name = "{}_commands".format(Text.underscore(getClass.getSimpleName))
}