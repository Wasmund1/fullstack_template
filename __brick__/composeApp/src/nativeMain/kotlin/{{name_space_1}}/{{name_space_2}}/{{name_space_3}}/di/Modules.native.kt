package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.di

import DbClient
import org.koin.dsl.module

actual val platformModule =
    module {
        single { DbClient() }
    }
