import bintray.Keys._

bintrayPublishSettings

// Project Description

organization in ThisBuild := "io.github.darkyenus"

name := "g8superplugin"

version in ThisBuild := "1.0"

description := "Use g8 as a sbt plugin"

homepage := Some(url("https://github.com/Darkyenus/g8SuperPlugin"))

startYear := Some(2015)

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

sbtPlugin := true

// Project Build

resolvers += Resolver.sonatypeRepo("releases")

libraryDependencies += ("net.databinder.giter8" %% "giter8" % "0.6.8")

// Project Publish

publishMavenStyle := false

repository in bintray := "sbt-plugins"

bintrayOrganization in bintray := None