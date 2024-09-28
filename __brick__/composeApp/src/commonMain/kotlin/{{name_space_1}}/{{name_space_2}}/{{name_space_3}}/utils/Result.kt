package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.utils

sealed class Result<T>(
    open val data: T? = null,
    open val message: String? = null,
) {
    data class Error<T>(override val data: T? = null, override val message: String) : Result<T>(data, message)

    data class Success<T>(override val data: T) : Result<T>(data)
}
