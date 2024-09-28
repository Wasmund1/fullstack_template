package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.navigation

sealed class Screen(val route: String) {
    data object Main : Screen("main_screen")

    data object Even : Screen("even_screen")

    data object Odd : Screen("odd_screen")

    data object Signup : Screen("sign_up_screen")

    data object Login : Screen("login_screen")

    data object Welcome : Screen("welcome_screen")

    data object Home : Screen("home_screen")
}
