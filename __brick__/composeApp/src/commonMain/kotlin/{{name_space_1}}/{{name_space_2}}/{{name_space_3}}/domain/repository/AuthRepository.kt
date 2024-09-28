package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.domain.repository

import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.model.AuthResultData
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.model.LoginParams
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.model.SignUpParams
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.utils.Result

interface AuthRepository {
    suspend fun signUp(params: SignUpParams): Result<AuthResultData>

    suspend fun login(params: LoginParams): Result<AuthResultData>

    suspend fun loginWithToken(): Result<AuthResultData>
}
