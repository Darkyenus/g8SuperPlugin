# g8SuperPlugin
plugin which integrates [giter8](https://github.com/n8han/giter8) template system directly into [sbt](http://www.scala-sbt.org)

## Installation
_Make sure that you have [sbt installed](http://www.scala-sbt.org/0.13/tutorial/Setup.html) before continuing_

For the best experience, install as a global sbt plugin, by creating a new file at `~/.sbt/0.13/plugins/g8SuperPlugin.sbt` containing:
```scala
addSbtPlugin("io.github.darkyenus" % "g8superplugin" % "1.0")
```
_Or you can append it into your `plugins.sbt` file, [it does not really matter](http://www.scala-sbt.org/0.13/tutorial/Using-Plugins.html)_

## Usage
Simply fire up sbt wherever you want to create a new project from [g8 template](https://github.com/n8han/giter8/wiki/giter8-templates) and use the `g8` command as usual.
All g8 flags are available.

Examples:
```bash
$ sbt g8 ajhager/libgdx-sbt-project
$ sbt g8 -f -b binary n8han/giter8.g8 --name="My new g8 template"
```
