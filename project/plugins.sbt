libraryDependencies += ((sbtBinaryVersion in pluginCrossBuild).value match {
  case "0.13" => "org.scala-sbt" %  "scripted-plugin" % sbtVersion.value
  case _      => "org.scala-sbt" %% "scripted-plugin" % sbtVersion.value
})

addSbtPlugin("org.foundweekends" % "sbt-bintray" % "0.5.3")
addSbtPlugin("com.github.gseitz" % "sbt-release" % "1.0.7")
