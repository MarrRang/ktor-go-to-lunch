package com.example

import com.example.configuration.clientModule
import com.example.module.catModule
import com.example.routing.cat
import com.typesafe.config.ConfigFactory
import io.ktor.application.*
import io.ktor.config.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.logger.Level
import org.koin.fileProperties
import org.koin.logger.SLF4JLogger

fun main() {
    embeddedServer(Netty, port = 8080) {
        stopKoin()
        startKoin {
            SLF4JLogger(Level.INFO)

            val config = HoconApplicationConfig(ConfigFactory.load())

            val profile = config.propertyOrNull("ktor.environment.profile")?.getString()

            if (profile.isNullOrBlank()) {
                log.error("No Profile Found!")
            }

            fileProperties("/application-$profile.properties")

            val catModules = arrayOf(clientModule, catModule)

            modules(*catModules)
        }

        registerRouters()
    }.start(true)
}

fun Application.registerRouters() {
    routing {
        cat()
    }
}
