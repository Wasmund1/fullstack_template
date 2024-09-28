package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.plugins

import io.ktor.server.application.Application
import io.ktor.server.routing.routing
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.route.authRouting

fun Application.configureRouting() {
    routing {
        authRouting()
    }
}
