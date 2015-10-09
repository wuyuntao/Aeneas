package com.wuyuntao.aeneas

/**
 * @author Wu Yuntao
 */
trait Model {
  def save(event: Event): Unit = {
    val batch = event.batch
    
    onSave(event)
    
    batch.execute
  }
  
  def onSave(event: Event): Unit
}