package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.presentation.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.presentation.ui.components.DefaultAuthScreen

@OptIn(KoinExperimentalAPI::class)
@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = koinViewModel(),
) {
    DefaultAuthScreen(navController = navController, viewModel = homeViewModel, title = "Home")
}
