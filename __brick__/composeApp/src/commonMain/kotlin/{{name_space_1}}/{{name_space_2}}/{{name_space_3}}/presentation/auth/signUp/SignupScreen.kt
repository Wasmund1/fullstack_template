package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.presentation.auth.signUp

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import {{appname}}.composeapp.generated.resources.Res
import {{appname}}.composeapp.generated.resources.button_signup
import {{appname}}.composeapp.generated.resources.label_email
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
fun SignupScreen(
    navController: NavController,
    signupViewModel: SignupViewModel = koinViewModel(),
) {
    val error = signupViewModel.error.collectAsState().value
    val isLoading = signupViewModel.isLoading.collectAsState().value
    val canSubmit by signupViewModel.canSubmit.collectAsState()
    val signUpParams by signupViewModel.signUpParams.collectAsState()

    val content: columnScope = {
        CustomTextField(
            initialValue = signUpParams.userName,
            label = stringResource(Res.string.label_user_name),
            onValueChange = signupViewModel::updateUsername,
        )
        CustomTextField(
            initialValue = signUpParams.email,
            label = stringResource(Res.string.label_email),
            onValueChange = signupViewModel::updateEmail,
        )

        PasswordTextField(
            label = stringResource(Res.string.label_password),
            onValueChange = signupViewModel::updatePassword,
        )
        if (error.isNotBlank()) Text(error)
        TextButtonWithHint(
            hintText = "Already have an account?",
            onClick = signupViewModel::navigateToLogin,
        )
    }
    val buttons: columnScope = {
        CustomButton(
            text = stringResource(Res.string.button_signup),
            onClick = signupViewModel::signUp,
            enabled = canSubmit,
        )
    }
    DefaultAuthScreen(
        isLoading,
        navController,
        signupViewModel,
        "Sign Up",
        content,
        buttons,
    )
}
