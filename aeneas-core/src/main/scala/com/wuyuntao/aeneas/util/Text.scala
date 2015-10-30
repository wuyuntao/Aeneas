package com.wuyuntao.aeneas.util

object Text {
  def underscore(word: String) = {
    val word2 = "([A-Z]+)([A-Z][a-z\\d])".r.replaceAllIn(word, "$1_$2")
    val word3 = "([a-z\\d])([A-Z])".r.replaceAllIn(word2, "$1_$2")
    val word4 = "[-_\\.]+".r.replaceAllIn(word3, "_")

    word4.toLowerCase
  }

  def camelize(word: String) = {
    "(?:^|[_-])(.)".r.replaceAllIn(word, { c => c.group(0).toUpperCase })
  }
}