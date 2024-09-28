package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.dao.auth

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.less
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.dao.DatabaseFactory.dbQuery
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.dao.RefreshTokenTable
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.model.TokenData

class RefreshTokenDaoImpl : RefreshTokenDao {
    override suspend fun insert(tokenData: TokenData, userName: String): TokenData? {
        return dbQuery {
            val insertStatement = RefreshTokenTable.insert {
                it[token] = tokenData.token
                it[expiration] = tokenData.expiration
            }
            insertStatement.resultedValues?.singleOrNull()?.let {
                rowToRefreshToken(it)
            }
        }
    }

    override suspend fun findByToken(tokenData: TokenData): TokenData? {
        return dbQuery {
            RefreshTokenTable.select {
                RefreshTokenTable.token eq tokenData.token
            }.map { rowToRefreshToken(it) }.singleOrNull()
        }
    }

    override suspend fun deleteToken(tokenData: TokenData): Int {
        return dbQuery {
            RefreshTokenTable.deleteWhere {
                token eq tokenData.token
            }
        }
    }

    override suspend fun deleteExpiredTokens(): Int {
        val now = System.currentTimeMillis()
        return dbQuery {
            RefreshTokenTable.deleteWhere {
                expiration less now
            }
        }
    }

    private fun rowToRefreshToken(row: ResultRow): TokenData = TokenData(
        token = row[RefreshTokenTable.token],
        expiration = row[RefreshTokenTable.expiration],
    )
}