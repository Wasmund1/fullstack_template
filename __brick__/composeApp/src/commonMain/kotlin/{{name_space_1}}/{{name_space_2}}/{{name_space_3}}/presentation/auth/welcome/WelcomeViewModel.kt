package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.presentation.auth.welcome

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.domain.entity.NavigationDestination
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.domain.usecase.auth.LoginWithTokenUseCase
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.domain.usecase.localstorage.WriteStringToDataStoreUseCase
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.model.toJson
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.presentation.viewmodel.ViewModelWithNavigation
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.utils.Result

abstract class WelcomeViewModel : ViewModelWithNavigation() {
    abstract fun navigateToLogin()

    abstract fun navigateToSignup()
}

class DefaultWelcomeViewModel(
    private val loginWithTokenUseCase: LoginWithTokenUseCase,
    private val writeStringToDataStoreUseCase: WriteStringToDataStoreUseCase,
) :
    WelcomeViewModel() {
    init {
        tryAuthenticate()
    }

    override fun navigateToLogin() {
        viewModelScope.launch {
            navigateTo(NavigationDestination.Login)
        }
    }

    override fun navigateToSignup() {
        viewModelScope.launch {
            navigateTo(NavigationDestination.Signup)
        }
    }

    private fun tryAuthenticate() {
        setIsLoading(true)
        viewModelScope.launch {
            when (val authResultData = loginWithTokenUseCase()) {
                is Result.Error -> {
                    println("errror :(  ${authResultData.message}")
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

    private suspend fun saveToken(tokenAsJson: String) {
        writeStringToDataStoreUseCase.invoke({{name_space_1}}.{{name_space_2}}.{{name_space_3}}.common.STORAGE_NAME_TOKEN, tokenAsJson)
    }
}
