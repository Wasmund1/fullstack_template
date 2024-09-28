package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import org.koin.core.annotation.KoinExperimentalAPI
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.navigation.NavigationGraph

@OptIn(KoinExperimentalAPI::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
        KoinContext {
            NavigationGraph()
        }
    }
}

// TODO USE HTTPS !!!
// TODO  Unique Username
// TODO Make Buttons in/active
