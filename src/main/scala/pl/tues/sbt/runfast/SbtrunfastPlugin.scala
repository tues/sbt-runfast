package pl.tues.sbt.runfast

import sbt._
import sbt.Keys._
import sbt.plugins.JvmPlugin

import scala.sys.process.Process

object SbtrunfastPlugin extends AutoPlugin {

  override def trigger = allRequirements
  override def requires = JvmPlugin

  object autoImport {
    val runfastGenerate  = taskKey[Option[File]]("Generates a runfast script in the target directory")
    val runfastFile      = settingKey[File]("File to which the script should be written")
    val runfastClasspath = taskKey[Seq[File]]("Classpath used by the script")
  }

  import autoImport._

  override lazy val projectSettings = Seq(
    runfastFile      := (target in Compile).value / "runfast.sh",
    runfastClasspath := (fullClasspath in Runtime).value.map(_.data),
    runfastGenerate  := (mainClass in Compile).value.map(
      RunfastGenerate(_, runfastFile.value, runfastClasspath.value)
    )
  )

  override lazy val buildSettings = Seq()

  override lazy val globalSettings = Seq()

}

object RunfastGenerate {

  def apply(main: String, runfastFile: File, classpath: Seq[File]): File = {
    IO.writeLines(runfastFile,
      Seq(
        "#!/bin/bash",
        "",
        s"""java -cp '${classpath.mkString(":")}' '${main}' "$$@""""
      )
    )

    Process(Seq("chmod", "a+x", runfastFile.getPath())).!

    runfastFile
  }

}
