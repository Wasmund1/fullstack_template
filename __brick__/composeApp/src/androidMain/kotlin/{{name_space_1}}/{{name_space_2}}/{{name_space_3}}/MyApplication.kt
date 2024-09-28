package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

class MyApplication : Application() {
    private val prefs: DataStore<Preferences> = createDataStore(this)

    override fun onCreate() {
        super.onCreate()
        initKoin(
            config = {
                androidContext(this@MyApplication)
            },
            prefsModule =
                module {
                    single<{{name_space_1}}.{{name_space_2}}.{{name_space_3}}.common.datastore.DataStoreService>(createdAtStart = true) {
                        {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.common.datastore.DataStoreServiceImpl(
                            prefs
                        )
                    }
                },
        )
    }
}
