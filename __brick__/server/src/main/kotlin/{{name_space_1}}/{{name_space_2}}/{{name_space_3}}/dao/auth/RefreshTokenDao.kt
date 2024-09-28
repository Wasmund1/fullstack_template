package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.dao.auth

import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.model.TokenData

interface RefreshTokenDao {
    suspend fun insert(tokenData: TokenData, userName: String): TokenData?
    suspend fun findByToken(tokenData: TokenData): TokenData?
    suspend fun deleteToken(tokenData: TokenData): Int
    suspend fun deleteExpiredTokens(): Int
}