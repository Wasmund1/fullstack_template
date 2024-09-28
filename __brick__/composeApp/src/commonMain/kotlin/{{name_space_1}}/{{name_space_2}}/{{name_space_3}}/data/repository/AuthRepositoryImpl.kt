package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.data.mapper.toAuthResultData
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.domain.repository.AuthRepository
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.model.AuthResultData
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.model.LoginParams
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.model.SignUpParams
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.utils.Result

internal class AuthRepositoryImpl(
    private val authService: {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.data.AuthService,
    private val tokenService: {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.data.TokenService,
) : AuthRepository {
    override suspend fun signUp(params: SignUpParams): Result<AuthResultData> {
        return withContext(Dispatchers.IO) {
            try {
                val authResponse = authService.signUp(params)
                val data = authResponse.data
                if (data == null) {
                    Result.Error(
                        message = authResponse.errorMessage!!,
                    )
                } else {
                    Result.Success<AuthResultData>(
                        data = data.toAuthResultData(),
                    )
                }
            } catch (e: Exception) {
                Result.Error(message = "Error ${e.message}")
            }
        }
    }

    override suspend fun login(params: LoginParams): Result<AuthResultData> {
        return withContext(Dispatchers.IO) {
            try {
                val authResponse = authService.logIn(params)
                val data = authResponse.data
                if (data == null) {
                    Result.Error(
                        message = authResponse.errorMessage!!,
                    )
                } else {
                    Result.Success(
                        data = data.toAuthResultData(),
                    )
                }
            } catch (e: Exception) {
                Result.Error(message = "Error ${e.message}")
            }
        }
    }

    override suspend fun loginWithToken(): Result<AuthResultData> {
        return withContext(Dispatchers.IO) {
            try {
                val token = tokenService.getAccessToken()
                val authResponse = authService.logInWithToken(token)
                val data = authResponse.data
                if (data == null) {
                    Result.Error(message = authResponse.errorMessage!!)
                } else {
                    Result.Success(data = data.toAuthResultData())
                }
            } catch (e: Exception) {
                Result.Error(message = "Error ${e.message}")
            }
        }
    }
}
