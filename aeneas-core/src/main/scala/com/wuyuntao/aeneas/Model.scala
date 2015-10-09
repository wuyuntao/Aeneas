package com.wuyuntao.aeneas

/**
 * @author Wu Yuntao
 */
trait Model {
  def process(command: Command): Option[Event]
}