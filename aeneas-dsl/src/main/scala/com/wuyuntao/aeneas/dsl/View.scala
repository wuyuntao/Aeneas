package com.wuyuntao.aeneas.dsl

import com.wuyuntao.aeneas.util.Text

abstract class View extends Table {
  def name = "{}_views".format(Text.underscore(getClass.getSimpleName))
}