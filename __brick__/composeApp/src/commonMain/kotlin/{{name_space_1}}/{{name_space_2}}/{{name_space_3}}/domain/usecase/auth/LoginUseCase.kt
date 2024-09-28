package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.domain.usecase.auth

import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.domain.repository.AuthRepository
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.model.AuthResultData
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.model.LoginParams
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.utils.Result

abstract class LoginUseCase {
    abstract suspend operator fun invoke(params: LoginParams): Result<AuthResultData>
}

class LoginUseCaseImpl(private val repository: AuthRepository) : LoginUseCase() {
    override suspend fun invoke(params: LoginParams): Result<AuthResultData> = repository.login(params)
}
