lazy val commonSettings = Def.settings(
  scalaVersion := "2.13.6"
)

lazy val myWarts = project.in(file("my-warts")).settings(
  commonSettings,
  libraryDependencies ++= Seq(
    "org.wartremover" % "wartremover" % wartremover.Wart.PluginVersion cross CrossVersion.full
  )
)

lazy val main = project.in(file("main")).settings(
  commonSettings,
  wartremoverWarnings += Wart.custom("mywarts.Unimplemented"),
  wartremover.WartRemover.dependsOnLocalProjectWarts(myWarts),
)
