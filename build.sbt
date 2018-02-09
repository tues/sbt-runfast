name := """sbt-runfast"""
organization := "pl.tues"
licenses += ("BSD 3-Clause", url("http://opensource.org/licenses/BSD-3-Clause"))

scalacOptions := Seq("-unchecked", "-deprecation", "-feature", "-encoding", "utf8")

crossSbtVersions := List("0.13.16", "1.0.4")
scalaVersion := ((sbtBinaryVersion in pluginCrossBuild).value match {
  case "0.13" => "2.10.4"
  case _      => "2.12.4"
})

sbtPlugin := true

libraryDependencies ++= Seq(
  "org.scalactic" %% "scalactic" % "3.0.4" % "test",
  "org.scalatest" %% "scalatest" % "3.0.4" % "test"
)

bintrayPackageLabels := Seq("sbt", "plugin")
bintrayVcsUrl := Some("""https://github.com/tues/sbt-runfast.git""")

initialCommands in console := """import pl.tues.sbt.runfast._"""

// set up 'scripted; sbt plugin for testing sbt plugins
ScriptedPlugin.scriptedSettings
scriptedLaunchOpts ++= Seq("-Xmx1024M", "-XX:MaxPermSize=256M", "-Dplugin.version=" + version.value)
