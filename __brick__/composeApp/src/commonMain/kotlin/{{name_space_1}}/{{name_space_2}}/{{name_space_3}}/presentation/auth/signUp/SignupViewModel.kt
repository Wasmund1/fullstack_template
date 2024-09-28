package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.presentation.auth.signUp

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.domain.entity.NavigationDestination
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.domain.usecase.auth.SignUpUseCase
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.domain.usecase.localstorage.WriteStringToDataStoreUseCase
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.model.SignUpParams
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.presentation.viewmodel.ViewModelWithNavigation
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.utils.Result

abstract class SignupViewModel : ViewModelWithNavigation() {
    abstract val signUpParams: StateFlow<SignUpParams>
    abstract val error: StateFlow<String>
    abstract val canSubmit: StateFlow<Boolean>

    abstract fun updateUsername(value: String)

    abstract fun updateEmail(value: String)

    abstract fun updatePassword(value: String)

    abstract fun navigateToLogin()

    abstract fun signUp()
}

class DefaultSignUpViewModel(
    private val signUpUseCase: SignUpUseCase,
    private val writeStringToDataStoreUseCase: WriteStringToDataStoreUseCase,
) : SignupViewModel() {
    override val signUpParams: MutableStateFlow<SignUpParams> =
        MutableStateFlow(SignUpParams("", "", ""))
    override val error: MutableStateFlow<String> = MutableStateFlow("")
    override val canSubmit: StateFlow<Boolean> =
        signUpParams.map { params ->
            params.userName.isNotBlank() && params.password.isNotBlank() && params.email.isNotBlank()
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            false,
        )

    override fun updateUsername(value: String) {
        signUpParams.value = signUpParams.value.copy(userName = value)
    }

    override fun updateEmail(value: String) {
        signUpParams.value = signUpParams.value.copy(email = value)
    }

    override fun updatePassword(value: String) {
        signUpParams.value = signUpParams.value.copy(password = value)
    }

    override fun navigateToLogin() {
        viewModelScope.launch {
            navigateTo(NavigationDestination.Login)
        }
    }

    override fun signUp() {
        println("username : ${signUpParams.value.userName}")
        println("email : ${signUpParams.value.email}")
        println("password : ${signUpParams.value.password}")
        setIsLoading(true)
        viewModelScope.launch {
            writeStringToDataStoreUseCase.invoke("password", signUpParams.value.password)
            when (val authResultData = signUpUseCase(signUpParams.value)) {
                is Result.Error -> {
                    println("errror :(  ${authResultData.message}")
                    error.value =
                        authResultData.message
                }

                is Result.Success -> {
                    println("success :)")
                    println("token : ${authResultData.data.token.accessTokenData.token}")
                    navigateTo(NavigationDestination.Home)
                }
            }
            setIsLoading(false)
        }
    }
}
