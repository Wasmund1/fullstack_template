package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.di

import io.ktor.server.application.Application
import io.ktor.server.application.install
import org.koin.ktor.plugin.Koin

fun Application.configureDI(){
    install(Koin){
            modules(appModule)
    }
}