package io.github.darkyenus

import sbt.Keys.commands
import sbt._

object G8SuperPlugin extends AutoPlugin {

  override def trigger: PluginTrigger = allRequirements

  override val globalSettings = Seq(commands += G8Command.g8Command)

}
