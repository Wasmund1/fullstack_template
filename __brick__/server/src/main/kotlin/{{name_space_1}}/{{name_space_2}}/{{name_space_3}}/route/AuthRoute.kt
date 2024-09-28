package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.route

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.TokenExpiredException
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.auth.authenticate
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.principal
import io.ktor.server.request.receiveNullable
import io.ktor.server.request.receiveText
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.serialization.json.Json
import org.koin.ktor.ext.inject
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.repository.auth.AuthRepository
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.common.Path
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.model.AuthResponse
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.model.LoginParams
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.model.SignUpParams
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.model.TokenData


@OptIn(DelicateCoroutinesApi::class)
fun Routing.authRouting() {
    val authRepository by inject<AuthRepository>()

    route(path = Path.SIGN_UP) {
        post {
            println(call.request.rawQueryParameters)
            val params = call.receiveNullable<SignUpParams>()
            if (params == null) {
                call.respond(
                    status = HttpStatusCode.BadRequest,
                    message = AuthResponse(errorMessage = "Invalid Credentials")
                )
                return@post
            }
            val result = authRepository.signUp(params = params)
            call.respond(
                status = result.statusCode,
                message = result.data
            )
        }
    }

    route(path = Path.LOGIN) {
        post {
            val params = call.receiveNullable<LoginParams>()
            if (params == null) {
                call.respond(
                    status = HttpStatusCode.BadRequest,
                    message = AuthResponse(errorMessage = "Invalid Credentials")
                )
                return@post
            }
            val result = authRepository.logIn(params = params)
            call.respond(
                status = result.statusCode,
                message = result.data
            )
        }
    }

    route(path = Path.LOGIN_WITH_TOKEN) {
        authenticate {
            get {
                val principal = call.principal<JWTPrincipal>()
                val username = principal?.getClaim("userName", String::class)
                println("username : $username")
                val result = username?.let { userName -> authRepository.getUser(userName) }
                if (result != null) {
                    call.respond(
                        status = result.statusCode,
                        message = result.data
                    )
                } else {
                    call.respond(
                        status = HttpStatusCode.BadRequest,
                        message = AuthResponse(errorMessage = "Invalid Credentials")
                    )
                }
            }
        }
    }

    route(path = Path.REFRESH) {
        post {
            val rawBody = call.receiveText()
            val tokenData: TokenData? = Json.decodeFromString(rawBody)
            val userName = tokenData?.let { it1 ->
                extractUserNameFromToken(it1.token, authRepository)
            }
            println("username : $userName")
            if (tokenData == null || userName == null) {
                call.respond(
                    status = HttpStatusCode.BadRequest,
                    message = AuthResponse(errorMessage = "Invalid Credentials")
                )
                return@post
            }
            val result = authRepository.refresh(tokenData = tokenData, userName = userName)
            call.respond(
                status = result.statusCode,
                message = result.data
            )
        }
    }

}

suspend fun extractUserNameFromToken(token: String, authRepository: AuthRepository): String? {
    try {
        val algorithm = Algorithm.HMAC256(System.getenv("JWT_SECRET"))
        val verifier = JWT.require(algorithm).build()
        val decodedJWT = verifier.verify(token)
        return decodedJWT.getClaim("userName").asString()
    } catch (e: Exception) {
        if (e is TokenExpiredException) {
            val deleted = authRepository.deleteExpiredRefreshTokens()
            println("lines deleted : $deleted")
        }
        println("Invalid token: $e ${e.message}")
        return null
    }
}

