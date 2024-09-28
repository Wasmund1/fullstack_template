package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.common.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

abstract class DataStoreService {
    abstract suspend fun editString(
        key: String,
        value: String,
    )

    abstract fun getString(key: String): Flow<String?>
}

class DataStoreServiceImpl(private val dataStore: DataStore<Preferences>) : {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.common.datastore.DataStoreService() {
    override suspend fun editString(
        key: String,
        value: String,
    ) {
        dataStore.edit {
            val preferencesKey = stringPreferencesKey(key)
            it[preferencesKey] = value
        }
    }

    override fun getString(key: String): Flow<String?> =
        dataStore.data.map {
            it[stringPreferencesKey(key)]
        }
}
