package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.domain.usecase.localstorage

import kotlinx.coroutines.flow.Flow

abstract class ReadStringFromDataStoreUseCase {
    abstract operator fun invoke(key: String): Flow<String?>
}

class ReadStringFromDataStoreUseCaseImpl(private val dataStoreService: {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.common.datastore.DataStoreService) :
    ReadStringFromDataStoreUseCase() {
    override fun invoke(key: String): Flow<String?> = dataStoreService.getString(key)
}
