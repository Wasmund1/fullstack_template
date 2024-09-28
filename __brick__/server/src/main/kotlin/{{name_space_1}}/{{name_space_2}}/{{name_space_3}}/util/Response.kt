package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.util

import io.ktor.http.HttpStatusCode

sealed class Response<T>(
    val statusCode: HttpStatusCode = HttpStatusCode.OK,
    val data: T
) {
    class Success<T>(data: T) : Response<T>(data = data)

    class Error<T>(
        code: HttpStatusCode,
        data: T
    ) : Response<T>(statusCode = code, data = data)
}