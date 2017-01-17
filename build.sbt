name := """archie-ai-web"""
organization := "ai.archie"

version := "1.0-SNAPSHOT"

lazy val SpeechRecognitionService = project in file("modules/SpeechRecognitionService")

lazy val VoiceBuilderService = project in file("modules/VoiceBuilderService")

lazy val root = (project in file(".")).enablePlugins(PlayScala)
  .dependsOn(SpeechRecognitionService, VoiceBuilderService)
  .aggregate(SpeechRecognitionService, VoiceBuilderService)

scalaVersion := "2.11.8"

libraryDependencies += filters
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.ptemplin.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.ptemplin.binders._"
