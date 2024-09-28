package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}

import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.dao.DatabaseFactory
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.di.configureDI
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.plugins.configureRouting
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.plugins.configureSecurity
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.plugins.configureSerialization

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    DatabaseFactory.init()
    configureSecurity()
    configureSerialization()
    configureDI()
    configureRouting()
}
