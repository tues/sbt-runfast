package pl.tues.sbt.runfast

import sbt._
import sbt.Keys._
import sbt.plugins.JvmPlugin

object SbtrunfastPlugin extends AutoPlugin {

  override def trigger = allRequirements
  override def requires = JvmPlugin

  object autoImport {
    val runfastGenerate = taskKey[Option[File]]("Generates a runfast script in the target directory")
  }

  import autoImport._

  override lazy val projectSettings = Seq(
    runfastGenerate := (mainClass in Compile).value.map { main =>
      val runfastFile = (target in Compile).value / "runfast.sh"
      val classpath = (fullClasspath in Runtime).value.map(_.data).mkString(":")

      IO.writeLines(runfastFile,
        Seq(
          "#!/bin/bash",
          "",
          s"""java -cp '${classpath}' '${main}' "$$@""""
        )
      )

      (s"""chmod a+x ${runfastFile}""" !)

      runfastFile
    }
  )

  override lazy val buildSettings = Seq()

  override lazy val globalSettings = Seq()

}
