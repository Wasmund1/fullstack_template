package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.dao

import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    fun init() {
        Database.connect(createHikariDataSource())
        transaction {
            SchemaUtils.create(UserTable, RefreshTokenTable)
        }
    }

    private fun createHikariDataSource(): HikariDataSource {
        val driverClass = "org.postgresql.Driver"
        val jdbcUrl = System.getenv("JDBC_URL")
        val hikariConfig = HikariDataSource().apply {
            username = System.getenv("DB_USER")
            password = System.getenv("DB_PASSWORD")
            driverClassName = driverClass
            setJdbcUrl(jdbcUrl)
            maximumPoolSize = 3
            isAutoCommit = false
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
            validate()
        }
        return HikariDataSource(hikariConfig)
    }

    suspend fun <T> dbQuery(block: suspend () -> T) = newSuspendedTransaction(Dispatchers.IO) {
        block()
    }
}

object RefreshTokenTable : Table(name = "refresh_tokens") {
    val token = varchar(name = "token", length = 512)
    val expiration = long(name = "timestamp")
}

object UserTable : Table(name = "users") {
    val id = integer(name = "user_id").autoIncrement()
    val name = varchar(name = "username", length = 250)
    val email = varchar(name = "user_email", length = 250)
    val bio = text(name = "user_bio").default(
        defaultValue = "Hey, what's up? Welcome to my SocialApp page!"
    )
    val password = varchar(name = "user_password", length = 100)
    val avatar = text(name = "user_avatar").nullable()

    override val primaryKey: PrimaryKey
        get() = PrimaryKey(id)
}