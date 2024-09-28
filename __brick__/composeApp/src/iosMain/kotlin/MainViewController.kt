
import androidx.compose.ui.window.ComposeUIViewController
import org.koin.dsl.module
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.App
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.di.initKoin

fun MainViewController() =
    ComposeUIViewController(
        configure = {
            val prefs = createDataStore()
            val prefsModule =
                module {
                    single<{{name_space_1}}.{{name_space_2}}.{{name_space_3}}.common.datastore.DataStoreService> {
                        {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.common.datastore.DataStoreServiceImpl(
                            prefs
                        )
                    }
                }
            initKoin(prefsModule = prefsModule)
        },
    ) {
        App()
    }
