
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
      "com.datastax.cassandra"  % "cassandra-driver-core" % "2.1.8",
      "ch.qos.logback" % "logback-classic" % "1.1.3",
      "com.github.scopt" %% "scopt" % "3.3.0",

      "com.typesafe.akka" %% "akka-persistence" % "2.4.0",
      "com.typesafe.akka" %% "akka-persistence-query-experimental" % "2.4.0",

      "junit" % "junit" % "4.11" % "test",
      "com.novocode" % "junit-interface" % "0.11" % "test"
    )  
  )

lazy val dsl = (project in file("aeneas-dsl")).
  dependsOn(core).
  settings(commonSettings: _*).
  settings(
    name := "aeneas-dsl",

    libraryDependencies ++= Seq(
      "org.reflections" % "reflections" % "0.9.10",

      "junit" % "junit" % "4.11" % "test",
      "com.novocode" % "junit-interface" % "0.11" % "test"
    )  
  )

lazy val cli = (project in file("aeneas-cli")).
  dependsOn(migration).
  settings(commonSettings: _*).
  settings(
    name := "aeneas-cli",

    libraryDependencies ++= Seq(
      "com.github.scopt" %% "scopt" % "3.3.0",
      
      "junit" % "junit" % "4.11" % "test",
      "com.novocode" % "junit-interface" % "0.11" % "test"
    )  
  )

lazy val migration = (project in file("aeneas-migration")).
  dependsOn(core).
  settings(commonSettings: _*).
  settings(
    name := "aeneas-migration",

    libraryDependencies ++= Seq(
      "org.reflections" % "reflections" % "0.9.10",

      "junit" % "junit" % "4.11" % "test",
      "com.novocode" % "junit-interface" % "0.11" % "test"
    )  
  )

lazy val example = (project in file("aeneas-example")).
  dependsOn(core).
  settings(commonSettings: _*).
  settings(
    name := "aeneas-example"
  )

lazy val dslExample = (project in file("aeneas-dsl-example")).
  dependsOn(dsl).
  settings(commonSettings: _*).
  settings(
    name := "aeneas-dsl-example"
  )

lazy val migrationExample = (project in file("aeneas-migration-example")).
  dependsOn(migration).
  settings(commonSettings: _*).
  settings(
    name := "aeneas-migration-example"
  )

