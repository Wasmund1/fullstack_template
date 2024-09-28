package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.di

import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration

fun initKoin(
    config: KoinAppDeclaration? = null,
    prefsModule: Module,
) {
    startKoin {
        config?.invoke(this)
        modules(prefsModule, presentationModule, domainModule, dataModule, platformModule)
    }
}
