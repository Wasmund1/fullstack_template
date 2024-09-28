package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.presentation.viewmodel.ViewModelWithNavigation

@Composable
fun NavController.handleNavigationFrom(viewmodel: ViewModelWithNavigation) {
    LaunchedEffect(viewmodel) {
        viewmodel.destination.collect {
            val route = destination(it)
            navigate(route)
        }
    }
}
