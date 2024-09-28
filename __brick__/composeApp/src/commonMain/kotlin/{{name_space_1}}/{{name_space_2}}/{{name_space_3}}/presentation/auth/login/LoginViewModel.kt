package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.presentation.auth.login

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.domain.entity.NavigationDestination
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.domain.usecase.auth.LoginUseCase
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.domain.usecase.localstorage.WriteStringToDataStoreUseCase
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.model.LoginParams
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.model.toJson
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.presentation.viewmodel.ViewModelWithNavigation
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.utils.Result

abstract class LoginViewModel : ViewModelWithNavigation() {
    abstract val loginParams: StateFlow<LoginParams>
    abstract val userName: StateFlow<String>
    abstract val password: StateFlow<String>
    abstract val error: StateFlow<String>
    abstract val canSubmit: StateFlow<Boolean>

    abstract fun updateUsername(value: String)

    abstract fun updatePassword(value: String)

    abstract fun navigateToSignUp()

    abstract fun login()
}

class DefaultLoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val writeStringToDataStoreUseCase: WriteStringToDataStoreUseCase,
) : LoginViewModel() {
    override val loginParams: MutableStateFlow<LoginParams> = MutableStateFlow(LoginParams("", ""))
    override val userName: MutableStateFlow<String> = MutableStateFlow("")
    override val password: MutableStateFlow<String> = MutableStateFlow("")
    override val error: MutableStateFlow<String> = MutableStateFlow("")
    override val canSubmit: StateFlow<Boolean> =
        loginParams
            .map { params ->
                params.userName.isNotBlank() && params.password.isNotBlank()
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = false,
            )

    override fun updateUsername(value: String) {
        loginParams.value = loginParams.value.copy(userName = value)
    }

    override fun updatePassword(value: String) {
        loginParams.value = loginParams.value.copy(password = value)
    }

    override fun navigateToSignUp() {
        viewModelScope.launch {
            navigateTo(NavigationDestination.Signup)
        }
    }

    override fun login() {
        println("username : ${loginParams.value.userName}")
        println("password : ${loginParams.value.password}")
        setIsLoading(true)
        viewModelScope.launch {
            when (val authResultData = loginUseCase(loginParams.value)) {
                is Result.Error -> {
                    println("errror :(  ${authResultData.message}")
                    error.value =
                        authResultData.message
                }

                is Result.Success -> {
                    println("success :)")
                    saveToken(authResultData.data.token.toJson())
                    navigateTo(NavigationDestination.Home)
                }
            }
            setIsLoading(false)
        }
    }

    private suspend fun saveToken(token: String) {
        writeStringToDataStoreUseCase.invoke({{name_space_1}}.{{name_space_2}}.{{name_space_3}}.common.STORAGE_NAME_TOKEN, token)
    }
}
