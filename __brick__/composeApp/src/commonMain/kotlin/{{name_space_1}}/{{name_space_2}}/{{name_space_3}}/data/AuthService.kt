package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.data


import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.common.Path
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.model.AuthResponse
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.model.LoginParams
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.model.SignUpParams
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.model.TokenData
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.model.TokenResponse

internal abstract class AuthService : {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.data.KtorApi() {
    abstract suspend fun signUp(params: SignUpParams): AuthResponse

    abstract suspend fun logIn(params: LoginParams): AuthResponse

    abstract suspend fun logInWithToken(token: String): AuthResponse

    abstract suspend fun refreshToken(refreshTokenData: TokenData): TokenResponse
}

internal class AuthServiceImpl : {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.data.AuthService() {
    override suspend fun signUp(params: SignUpParams): AuthResponse =
        client.post {
            endpoint(path = Path.SIGN_UP)
            setBody(params)
        }.body()

    override suspend fun logIn(params: LoginParams): AuthResponse =
        client.post {
            endpoint(path = Path.LOGIN)
            setBody(params)
        }.body()

    override suspend fun logInWithToken(token: String): AuthResponse =
        client.get {
            endpoint(path = Path.LOGIN_WITH_TOKEN)
            setToken(token)
        }.body()

    override suspend fun refreshToken(refreshTokenData: TokenData): TokenResponse =
        client.post {
            endpoint(path = Path.REFRESH)
            setBody(refreshTokenData)
        }.body()
}
