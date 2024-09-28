package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.domain.usecase.localstorage

abstract class WriteStringToDataStoreUseCase {
    abstract suspend operator fun invoke(
        key: String,
        value: String,
    )
}

class WriteStringToDataStoreUseCaseImpl(private val dataStoreService: {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.common.datastore.DataStoreService) :
    WriteStringToDataStoreUseCase() {
    override suspend fun invoke(
        key: String,
        value: String,
    ) = dataStoreService.editString(key, value)
}
