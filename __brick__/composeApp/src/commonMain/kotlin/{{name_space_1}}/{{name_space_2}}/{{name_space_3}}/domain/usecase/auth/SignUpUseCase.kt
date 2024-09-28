package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.domain.usecase.auth

import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.domain.repository.AuthRepository
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.model.AuthResultData
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.model.SignUpParams
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.utils.Result

abstract class SignUpUseCase {
    abstract suspend operator fun invoke(params: SignUpParams): Result<AuthResultData>
}

class SignUpUseCaseImpl(private val repository: AuthRepository) : SignUpUseCase() {
    override suspend fun invoke(params: SignUpParams): Result<AuthResultData> {
        if (params.userName.isBlank() || params.userName.length < 3) {
            return Result.Error(
                message = "Invalid name",
            )
        }
        if (params.email.isBlank() || "@" !in params.email) {
            return Result.Error(
                message = "Invalid email",
            )
        }
        if (params.password.isBlank() || params.password.length < 4) {
            return Result.Error(
                message = "Invalid password or too short!",
            )
        }
        return repository.signUp(params)
    }
}
