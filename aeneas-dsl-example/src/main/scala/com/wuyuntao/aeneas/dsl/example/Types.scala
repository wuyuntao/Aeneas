package com.wuyuntao.aeneas.dsl.example

import com.wuyuntao.aeneas.dsl.{ UserDefinedType, Column }

class IPAddress extends UserDefinedType {
  val host = Column[String](1) // 192.168.11.33
  val port = Column[Int](1) // 8000
}

class Device extends UserDefinedType {
  override def name = "device_alias" // use custom name of type

  val platform = Column[String](1) // Android, iOS, Windows ...
  val version = Column[String](1) // 5.1.0, 9.0.2, 10 ...
  val deviceIdentifier = Column[String](1) // Unique Identifier of device
  val deviceDescription = Column[String](2) // Description of device
}
