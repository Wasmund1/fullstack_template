package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class SignUpParams(
    val userName: String,
    val email: String,
    val password: String
)

@Serializable
data class LoginParams(
    val userName: String,
    val password: String
)

@Serializable
data class AuthResponse(
    val data: AuthResponseData? = null,
    val errorMessage: String? = null
)

data class AuthResultData(
    val id: Int,
    val name: String,
    val bio: String,
    val avatar: String? = null,
    val token: TokenResponseData,
    val followerCount: Int = 0,
    val followingCount: Int = 0,
)



@Serializable
data class AuthResponseData(
    val id: Int,
    val name: String,
    val bio: String,
    val avatar: String? = null,
    val token: TokenResponseData,
    val followerCount: Int = 0,
    val followingCount: Int = 0
)
@Serializable
data class TokenData(
    val token: String,
    val expiration: Long
)

@Serializable
data class TokenResponse (
    val data: TokenResponseData? = null,
    val errorMessage: String? = null,
)

fun TokenResponseData.toJson(): String = Json.encodeToString(this)

class TokenResponseSerializer {

    companion object {
        fun fromJson(json: String): TokenResponseData = Json.decodeFromString(json)
    }
}

@Serializable
data class TokenResponseData(
    val accessTokenData: TokenData,
    val refreshTokenData: TokenData,
)


