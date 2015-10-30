package com.wuyuntao.aeneas.dsl

import com.wuyuntao.aeneas.util.Text

abstract class Event extends Table {
  def name = "{}_events".format(Text.underscore(getClass.getSimpleName))
}