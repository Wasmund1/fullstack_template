package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.navigation

// import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.presentation.auth.login.LoginScreen
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.presentation.auth.signUp.SignupScreen
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.presentation.auth.welcome.WelcomeScreen
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.presentation.home.HomeScreen
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.presentation.ui.main.EvenScreen
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.presentation.ui.main.MainScreen
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.presentation.ui.main.OddScreen

@Composable
fun NavigationGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Welcome.route,
    ) {
        composable(
            route = Screen.Main.route,
        ) {
            //  BackHandler(true) {}
            MainScreen(navController = navController)
        }
        composable(
            route = Screen.Signup.route,
        ) {
            SignupScreen(navController = navController)
        }
        composable(
            route = Screen.Login.route,
        ) {
            LoginScreen(navController = navController)
        }
        composable(
            route = Screen.Welcome.route,
        ) {
            WelcomeScreen(navController = navController)
        }
        composable(
            route = Screen.Even.route,
        ) {
            EvenScreen(navController = navController)
        }
        composable(
            route = Screen.Odd.route,
        ) {
            OddScreen(navController = navController)
        }
        composable(
            route = Screen.Home.route,
        ) {
            HomeScreen(navController = navController)
        }
    }
}
