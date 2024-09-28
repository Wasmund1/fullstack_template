package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.presentation.auth.welcome

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import {{appname}}.composeapp.generated.resources.Res
import {{appname}}.composeapp.generated.resources.button_login
import {{appname}}.composeapp.generated.resources.button_signup
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.presentation.ui.components.DefaultAuthScreen
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.presentation.ui.components.button.CustomButton
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.utils.columnScope

@OptIn(KoinExperimentalAPI::class)
@Composable
fun WelcomeScreen(
    navController: NavController,
    welcomeViewModel: WelcomeViewModel = koinViewModel(),
) {
    val buttons: columnScope = {
        CustomButton(
            text = stringResource(Res.string.button_signup),
            onClick = welcomeViewModel::navigateToSignup,
        )
        CustomButton(
            text = stringResource(Res.string.button_login),
            onClick = welcomeViewModel::navigateToLogin,
        )
    }
    DefaultAuthScreen(
        isLoading = welcomeViewModel.isLoading.collectAsState().value,
        navController,
        welcomeViewModel,
        "Welcome",
        buttons = buttons,
    )
}
