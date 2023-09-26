package app.example

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.mordant.terminal.Terminal


object App : CliktCommand(name = "app example") {

  override fun run() {
    Terminal().println("Hello native world")
  }
}

fun main(args: Array<String>) {
  App.main(args)
}

