package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.domain.usecase.auth

import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.domain.repository.AuthRepository
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.model.AuthResultData
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.utils.Result

abstract class LoginWithTokenUseCase {
    abstract suspend operator fun invoke(): Result<AuthResultData>
}

class LoginWithTokenUseCaseImpl(private val repository: AuthRepository) : LoginWithTokenUseCase() {
    override suspend operator fun invoke(): Result<AuthResultData> = repository.loginWithToken()
}
