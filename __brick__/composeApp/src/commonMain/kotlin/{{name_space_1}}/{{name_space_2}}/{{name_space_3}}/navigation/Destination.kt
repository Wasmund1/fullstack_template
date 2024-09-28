package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.navigation


import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.domain.entity.NavigationDestination

fun destination(destination: NavigationDestination): String {
    return when (destination) {
        NavigationDestination.Even -> Screen.Even.route
        NavigationDestination.Main -> Screen.Main.route
        NavigationDestination.Odd -> Screen.Odd.route
        NavigationDestination.Login -> Screen.Login.route
        NavigationDestination.Signup -> Screen.Signup.route
        NavigationDestination.Welcome -> Screen.Welcome.route
        NavigationDestination.Home -> Screen.Home.route
    }
}
