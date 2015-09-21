package io.github.darkyenus

import giter8.Config
import sbt.complete.DefaultParsers._
import sbt.complete.Parser
import sbt.{Command, Help, State}

protected object G8Command {
  private val g8CommandName = "g8"

  private val g8CommandHelp = Help(g8CommandName, (g8CommandName, "Create project from giter8 template in current directory"), giter8.Giter8.parser.usage)

  private val forceFlagParser: Parser[Config => Config] =
    (literal("-f") || literal("--force")).map(_ => c => Config(c.repo, c.branch, c.tag, forceOverwrite = true, search = c.search))

  private val branchFlagParser: Parser[Config => Config] =
    ((literal("-b ") || literal("--branch ")) ~> StringBasic.examples("<branch>")).map(branch => c => Config(c.repo, Some(branch), c.tag, c.forceOverwrite, search = c.search))

  private val tagFlagParser: Parser[Config => Config] =
    ((literal("-t ") || literal("--tag ")) ~> StringBasic.examples("<tag>")).map(tag => c => Config(c.repo, c.branch, Some(tag), c.forceOverwrite, search = c.search))


  private val optionParser: Parser[Config => Config] = {
    (Space ~> (forceFlagParser || branchFlagParser || tagFlagParser)).map {
      case Left(Left(r)) => r
      case Left(Right(r)) => r
      case Right(r) => r
    }
  }

  //This one supports quoting!
  private val parameterParser: Parser[String] = {
    (Space ~> literal("--") ~> NotQuoted.examples("<key>") ~ literal('=') ~ StringBasic.examples("<value>")).map(param => s"--${param._1._1}=${param._2}")
  }

  private val g8CommandParser = (s: State) => {
    OptSpace ~> (literal("search").? ~ optionParser.* ~ (Space ~> StringBasic.examples("<template>")) ~ parameterParser.*)
  }

  def g8Command = Command(g8CommandName, g8CommandHelp)(g8CommandParser)((state, parsed) => {
    try {
      val (((search, options), repo), parameters) = parsed

      val config = options.foldLeft(new Config(repo = repo, search = search.isDefined))((config, flag) => flag(config))

      if (config.search) {
        giter8.Giter8.search(config) match {
          case Left(error) =>
            state.log.error(s"Search failed: $error")
          case Right(found) if found.trim.isEmpty =>
            println( s"""Didn't find any templates matching "$repo"""")
          case Right(found) =>
            println(found)
        }
      } else {
        giter8.Giter8.inspect(config, parameters) match {
          case Left(error) if error.trim.isEmpty =>
            state.log.error("Unknown error occurred")
          case Left(error) =>
            state.log.error(error)
          case Right(success) if success.trim.isEmpty =>
            println("Done")
          case Right(success) =>
            println(success)
        }
      }
    } finally {
      giter8.Giter8.cleanup()
    }
    state
  })
}
