package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class SignUpParams(
    var userName: String = "",
    var email: String = "",
    var password: String = "",
)
