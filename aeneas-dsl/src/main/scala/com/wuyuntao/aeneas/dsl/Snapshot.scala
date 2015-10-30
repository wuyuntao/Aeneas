package com.wuyuntao.aeneas.dsl

import com.wuyuntao.aeneas.util.Text

abstract class Snapshot extends Table {
  def name = "{}_snapshots".format(Text.underscore(getClass.getSimpleName))
}