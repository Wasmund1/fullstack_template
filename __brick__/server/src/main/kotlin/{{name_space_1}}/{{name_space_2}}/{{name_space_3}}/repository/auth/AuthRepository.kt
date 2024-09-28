package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.repository.auth

import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.model.AuthResponse
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.model.LoginParams
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.model.SignUpParams
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.model.TokenData
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.model.TokenResponse
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.util.Response

interface AuthRepository {
    suspend fun signUp(params: SignUpParams): Response<AuthResponse>
    suspend fun logIn(params: LoginParams): Response<AuthResponse>
    suspend fun getUser(userName: String): Response<AuthResponse>
    suspend fun refresh(tokenData: TokenData, userName: String): Response<TokenResponse>
    suspend fun deleteExpiredRefreshTokens(): Int
}