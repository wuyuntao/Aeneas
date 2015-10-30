package com.wuyuntao.aeneas.dsl.example

import com.wuyuntao.aeneas.dsl.{ UserDefinedType, Column }

class IPAddress extends UserDefinedType {
  val host = Column[String](1)
  val port = Column[Int](1)
}

class Device extends UserDefinedType {
  val platform = Column[String](1) // Android, iOS ...
  val version = Column[String](1) // 5.1.0, 9.0.2 ...
  val deviceIdentifier = Column[String](1) // Unique Identifier of device
  val deviceDescription = Column[String](2) // Description of device
}
