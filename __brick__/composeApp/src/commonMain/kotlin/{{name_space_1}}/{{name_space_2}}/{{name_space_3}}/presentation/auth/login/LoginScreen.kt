package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.presentation.auth.login

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import {{appname}}.composeapp.generated.resources.Res
import {{appname}}.composeapp.generated.resources.button_login
import {{appname}}.composeapp.generated.resources.label_password
import {{appname}}.composeapp.generated.resources.label_user_name
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.presentation.ui.components.DefaultAuthScreen
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.presentation.ui.components.button.CustomButton
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.presentation.ui.components.button.TextButtonWithHint
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.presentation.ui.components.textfield.CustomTextField
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.presentation.ui.components.textfield.PasswordTextField
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.utils.columnScope

@OptIn(KoinExperimentalAPI::class)
@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = koinViewModel(),
) {
    val isLoading = loginViewModel.isLoading.collectAsState().value
    val error = loginViewModel.error.collectAsState().value
    val canSubmit by loginViewModel.canSubmit.collectAsState()
    val content: columnScope = {
        CustomTextField(
            label = stringResource(Res.string.label_user_name),
            onValueChange = loginViewModel::updateUsername,
        )
        PasswordTextField(
            label = stringResource(Res.string.label_password),
            onValueChange = loginViewModel::updatePassword,
        )
        if (error.isNotBlank()) Text(error)
        TextButtonWithHint(
            hintText = "Don't have an account?",
            onClick = loginViewModel::navigateToSignUp,
        )
    }

    val buttons: columnScope = {
        CustomButton(
            text = stringResource(Res.string.button_login),
            onClick = loginViewModel::login,
            enabled = canSubmit,
        )
    }
    DefaultAuthScreen(isLoading, navController, loginViewModel, "Login", content, buttons)
}
