package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.repository.auth

import io.ktor.http.HttpStatusCode
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.dao.auth.RefreshTokenDao
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.dao.user.UserDao
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.plugins.generateTokenData
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.security.hashPassword
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.common.ACCESS_TOKEN_EXPIRATION_TIME
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.common.REFRESH_TOKEN_EXPIRATION_TIME
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.model.AuthResponse
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.model.AuthResponseData
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.model.LoginParams
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.model.SignUpParams
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.model.TokenData
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.model.TokenResponse
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.model.TokenResponseData
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.util.Response

class AuthRepositoryImpl(private val userDao: UserDao, private val refreshTokenDao: RefreshTokenDao) :
    AuthRepository {
    override suspend fun signUp(params: SignUpParams): Response<AuthResponse> {
        return if (userAlreadyExists(params.email)) {
            Response.Error(
                code = HttpStatusCode.Conflict,
                data = AuthResponse(errorMessage = "A User with this email already exists!")
            )
        } else {
            val insertedUser = userDao.insert(params)
            if (insertedUser == null) {
                Response.Error(
                    code = HttpStatusCode.InternalServerError,
                    data = AuthResponse(errorMessage = "Sorry")
                )
            } else {
                Response.Success(
                    data = AuthResponse(
                        data = AuthResponseData(
                            id = insertedUser.id,
                            name = insertedUser.name,
                            bio = insertedUser.bio,
                            token = generateTokenResponse(params.userName)
                        )
                    )
                )
            }
        }
    }

    override suspend fun logIn(params: LoginParams): Response<AuthResponse> {
        println("userName : ${params.userName}")
        val user = userDao.findByUserName(params.userName)
        if (user == null) {
            return Response.Error(
                code = HttpStatusCode.NotFound,
                data = AuthResponse(errorMessage = "User with this username does not exist")
            )
        } else {
            val hashedPassword = params.password.hashPassword()
            if (user.password == hashedPassword) {
                val tokenResponseData = generateTokenResponse(params.userName)
                refreshTokenDao.insert(tokenResponseData.refreshTokenData, params.userName)
                return Response.Success(
                    data = AuthResponse(
                        data = AuthResponseData(
                            id = user.id,
                            name = user.name,
                            bio = user.bio,
                            token = tokenResponseData
                        )
                    )
                )
            } else {
                return Response.Error(
                    code = HttpStatusCode.Forbidden,
                    data = AuthResponse(errorMessage = "Username/Password incorrect")
                )
            }
        }
    }

    override suspend fun getUser(userName: String): Response<AuthResponse> {
        val user = userDao.findByUserName(userName)
        return if (user == null) {
            Response.Error(
                code = HttpStatusCode.NotFound,
                data = AuthResponse(errorMessage = "There was an Error")
            )
        } else {
            Response.Success(
                data = AuthResponse(
                    data = AuthResponseData(
                        id = user.id,
                        name = user.name,
                        bio = user.bio,
                        token = generateTokenResponse(userName)
                    )
                )
            )
        }
    }

    override suspend fun refresh(tokenData: TokenData, userName: String): Response<TokenResponse> {
        if (refreshTokenDao.findByToken(tokenData) != null) {
            val newTokenResponse = generateTokenResponse(userName)
            //TODO delete ALL old refreshtoken from table
            if (refreshTokenDao.deleteToken(tokenData) == 0) {
                println("----------------------------could not delete token...")
            } else {
                println("----------------------------Token deleted")
            }
            refreshTokenDao.insert(newTokenResponse.refreshTokenData, userName)
            return Response.Success(
                data = TokenResponse(newTokenResponse)
            )
        } else {
            return Response.Error(
                code = HttpStatusCode.Unauthorized,
                data = TokenResponse(errorMessage = "Invalid token!")
            )
        }
    }

    override suspend fun deleteExpiredRefreshTokens(): Int = refreshTokenDao.deleteExpiredTokens()


    private suspend fun userAlreadyExists(email: String): Boolean {
        val result = userDao.findByEmail(email) != null
        return result
    }

    private fun generateTokenResponse(userName: String): TokenResponseData {
        val accessTokenData = generateTokenData(userName, ACCESS_TOKEN_EXPIRATION_TIME)
        val refreshTokenData = generateTokenData(userName, REFRESH_TOKEN_EXPIRATION_TIME)
        return TokenResponseData(accessTokenData, refreshTokenData)
    }
}