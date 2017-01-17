name := "SpeechRecognitionService"

lazy val SpeechRecognitionService = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.8"

libraryDependencies += filters
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test
libraryDependencies += "de.sciss" % "sphinx4" % "1.0.0"

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.ptemplin.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.ptemplin.binders._"
