package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.domain.entity

sealed class NavigationDestination {
    data object Main : NavigationDestination()

    data object Even : NavigationDestination()

    data object Odd : NavigationDestination()

    data object Signup : NavigationDestination()

    data object Login : NavigationDestination()

    data object Welcome : NavigationDestination()

    data object Home : NavigationDestination()
}
