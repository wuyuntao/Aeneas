
lazy val commonSettings = Seq(
  organization := "com.wuyuntao",
  version := "0.0.1",
  scalaVersion := "2.11.7",

  resolvers += Resolver.sonatypeRepo("public")
)

lazy val core = (project in file("aeneas-core")).
  settings(commonSettings: _*).
  settings(
    name := "aeneas",

    libraryDependencies ++= Seq(
      "com.datastax.cassandra"  % "cassandra-driver-core" % "2.1.7",
      "com.typesafe" % "config" % "1.3.0",

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

lazy val dsl = (project in file("aeneas-dsl")).
  settings(commonSettings: _*).
  settings(
    name := "aeneas-dsl",

    libraryDependencies ++= Seq(
      "junit" % "junit" % "4.11" % "test",
      "com.novocode" % "junit-interface" % "0.11" % "test"
    )  
  )

lazy val dslExample = (project in file("aeneas-dsl-example")).
  dependsOn(dsl).
  settings(commonSettings: _*).
  settings(
    name := "aeneas-dsl-examples"
  )

lazy val dslCodeGen = (project in file("aeneas-dsl-codegen")).
  dependsOn(dsl).
  settings(commonSettings: _*).
  settings(
    name := "aeneas-dsl-codegen",

    libraryDependencies ++= Seq(
      "com.github.scopt" %% "scopt" % "3.3.0",
      
      "junit" % "junit" % "4.11" % "test",
      "com.novocode" % "junit-interface" % "0.11" % "test"
    )  
  )