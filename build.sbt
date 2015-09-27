
lazy val commonSettings = Seq(
  organization := "com.wuyuntao",
  version := "0.0.1",
  scalaVersion := "2.11.7",
  unmanagedBase := baseDirectory.value / "lib"
)

lazy val core = (project in file("aeneas-core")).
  settings(commonSettings: _*).
  settings(
    name := "aeneas",

    libraryDependencies ++= Seq(
      "com.datastax.cassandra"  % "cassandra-driver-core" % "2.1.7",

      "junit" % "junit" % "4.11" % "test",
      "com.novocode" % "junit-interface" % "0.11" % "test"
    )  
  )

lazy val examples = (project in file("aeneas-examples")).
  dependsOn(core).
  settings(commonSettings: _*).
  settings(
    name := "aeneas-examples"
  )
