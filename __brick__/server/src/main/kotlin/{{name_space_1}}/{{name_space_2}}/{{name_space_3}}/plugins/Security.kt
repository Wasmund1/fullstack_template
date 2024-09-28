package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.auth.authentication
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.jwt.jwt
import io.ktor.server.response.respond
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.common.ACCESS_TOKEN_EXPIRATION_TIME
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.model.AuthResponse
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.model.TokenData
import java.util.Date

private val jwtAudience = System.getenv("JWT_AUDIENCE")
private val jwtSecret = System.getenv("JWT_SECRET")
private val jwtIssuer = System.getenv("JWT_DOMAIN")

private const val CLAIM = "userName"
fun Application.configureSecurity() {

    // Please read the jwt property from the config file if you are using EngineMain

    val jwtRealm = "ktor sample app"
    authentication {
        jwt {
            verifier(
                JWT
                    .require(Algorithm.HMAC256(jwtSecret))
                    .withAudience(jwtAudience)
                    .withIssuer(jwtIssuer)
                    .build()
            )
            validate { credential ->
                if (credential.payload.getClaim(CLAIM).asString() != null){
                    JWTPrincipal(payload = credential.payload)
                } else {
                    null
                }
            }
            challenge{ _,_ ->
                call.respond(
                    status = HttpStatusCode.Unauthorized,
                    message = AuthResponse(
                        errorMessage = "Token is not valid or has expired"
                    )
                )
            }
        }
    }
}

fun generateTokenData(userName: String, expireIn : Long = ACCESS_TOKEN_EXPIRATION_TIME) : TokenData {
    val expiration = System.currentTimeMillis() + expireIn
    val token =  JWT.create()
        .withAudience(jwtAudience)
        .withIssuer(jwtIssuer)
        .withClaim(CLAIM, userName)
        .withExpiresAt(Date(expiration))
        .sign(Algorithm.HMAC256(
        jwtSecret
        ))
    return TokenData(token, expiration)
}
