package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.navigation.handleNavigationFrom
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.presentation.viewmodel.ViewModelWithNavigation
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.utils.columnScope

@Composable
fun DefaultAuthScreen(
    isLoading: Boolean = false,
    navController: NavController,
    viewModel: ViewModelWithNavigation,
    title: String,
    content: columnScope? = null,
    buttons: columnScope? = null,
) {
    navController.handleNavigationFrom(viewModel)
    Scaffold {
        if (isLoading) {
            CircularProgressIndicator()
        } else {
            Column(
                modifier = Modifier.padding(it).padding(vertical = 24.dp, horizontal = 24.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
            ) {
                Text(title, style = MaterialTheme.typography.headlineLarge)
                Spacer(modifier = Modifier.height(24.dp))
                if (content != null) {
                    content()
                }
                Spacer(modifier = Modifier.weight(1f))
                if (buttons != null) {
                    buttons()
                }
            }
        }
    }
}
