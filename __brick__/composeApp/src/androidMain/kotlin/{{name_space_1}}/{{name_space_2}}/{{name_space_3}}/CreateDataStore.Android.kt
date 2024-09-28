package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

fun createDataStore(context: Context): DataStore<Preferences> {
    return {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.common.datastore.createDataStore {
        context.filesDir.resolve({{name_space_1}}.{{name_space_2}}.{{name_space_3}}.common.datastore.DATA_STORE_FILE_NAME).absolutePath
    }
}
