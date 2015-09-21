organization := "com.github.Darkyenus"

name := "g8SuperPlugin"

version := "1.0"

sbtPlugin := true

resolvers += Resolver.sonatypeRepo("releases")

libraryDependencies += ("net.databinder.giter8" %% "giter8" % "0.6.8")