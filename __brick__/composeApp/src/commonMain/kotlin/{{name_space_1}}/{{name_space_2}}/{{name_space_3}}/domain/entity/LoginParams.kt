package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class LoginParams(
    var userName: String = "",
    var password: String = "",
)
