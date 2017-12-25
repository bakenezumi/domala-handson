//lazy val root = (project in file(".")).enablePlugins(PlayScala).settings(
lazy val root = (project in file(".")).settings(
  inThisBuild(List(
    scalaVersion := "2.12.4",
    version := "0.1.0"
  )),
  name := "domala-handson",
  libraryDependencies ++= Seq(
    "com.h2database" % "h2" % "1.4.196",
    "com.typesafe.play" %% "play" % "2.6.9"
    // , guice
    // , jdbc
    // , evolutions
  )
) dependsOn repository aggregate repository

lazy val repository = (project in file("repository")).settings(
  inThisBuild(List(
    scalaVersion := "2.12.4",
    version := "0.1.0"
  )),
  metaMacroSettings,
  libraryDependencies ++= Seq(
    "org.scalameta" %% "scalameta" % "1.8.0" % Provided,
    "com.github.domala" %% "domala" % "0.1.0-beta.8"
  )
)

lazy val metaMacroSettings: Seq[Def.Setting[_]] = Seq(
  addCompilerPlugin("org.scalameta" % "paradise" % "3.0.0-M10" cross CrossVersion.full),
  scalacOptions += "-Xplugin-require:macroparadise",
  scalacOptions in (Compile, console) ~= (_ filterNot (_ contains "paradise")) // macroparadise plugin doesn't work in repl yet.
)
