package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.data

import kotlinx.coroutines.flow.first
import kotlinx.datetime.Clock
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.model.TokenData
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.model.TokenResponseData
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.model.TokenResponseSerializer
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.model.toJson

internal abstract class TokenService {
    abstract suspend fun getAccessToken(): String
}

internal class TokenServiceImpl(
    private val dataStoreService: {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.common.datastore.DataStoreService,
    private val authService: {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.data.AuthService,
) : {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.data.TokenService() {
    override suspend fun getAccessToken(): String {
        val tokenResponseData =
            getTokenResponseDataFromStorage() ?: throw {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.data.LocalStorageReadException()
        if (tokenNotExpired(tokenResponseData.accessTokenData.expiration)) {
            return tokenResponseData.accessTokenData.token
        } else {
            val newTokenResponseData =
                refreshToken(tokenResponseData.refreshTokenData) ?: throw {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.data.TokenRefreshException()
            println("Token refreshed!!!")
            writeTokenTGoStorage(newTokenResponseData)
            return newTokenResponseData.accessTokenData.token
        }
    }

    private fun tokenNotExpired(expiration: Long): Boolean = Clock.System.now().toEpochMilliseconds() + 5000 < expiration

    private suspend fun refreshToken(refreshToken: TokenData): TokenResponseData? = authService.refreshToken(refreshToken).data

    private suspend fun writeTokenTGoStorage(tokenResponseData: TokenResponseData) =
        dataStoreService.editString(
            {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.common.STORAGE_NAME_TOKEN,
            tokenResponseData.toJson(),
        )

    private suspend fun getTokenResponseDataFromStorage(): TokenResponseData? {
        val tokenResponseDataJson = dataStoreService.getString({{name_space_1}}.{{name_space_2}}.{{name_space_3}}.common.STORAGE_NAME_TOKEN).first()
        return tokenResponseDataJson?.let { TokenResponseSerializer.fromJson(it) }
    }
}

class LocalStorageReadException(message: String? = "Could not read token from storage") :
    Exception(message)

class TokenRefreshException(message: String? = "Error while trying to refresh access token") :
    Exception(message)
