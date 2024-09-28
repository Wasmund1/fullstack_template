import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.common.datastore.DATA_STORE_FILE_NAME
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

@OptIn(ExperimentalForeignApi::class)
fun createDataStore(): DataStore<Preferences> {
    return {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.common.datastore.createDataStore {
        val directory = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null,
        )
        requireNotNull(directory).path + "/$DATA_STORE_FILE_NAME"
    }
}
