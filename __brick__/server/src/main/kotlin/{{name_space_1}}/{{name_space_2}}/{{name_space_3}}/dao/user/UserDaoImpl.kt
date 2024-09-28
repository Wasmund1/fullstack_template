package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.dao.user

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.dao.DatabaseFactory.dbQuery
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.dao.UserTable
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.security.hashPassword
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.model.SignUpParams
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.model.User

class UserDaoImpl : UserDao {
    override suspend fun insert(params: SignUpParams): User? {
        return dbQuery {
            val insertStatement = UserTable.insert {
                it[name] = params.userName
                it[email] = params.email
                it[password] = params.password.hashPassword()
            }
            insertStatement.resultedValues?.singleOrNull()?.let {
                rowToUser(it)
            }
        }
    }

    override suspend fun findByEmail(email: String): User? {

        return dbQuery {
            UserTable.select { UserTable.email eq email }
                .map { rowToUser(it) }
                .singleOrNull()
        }
    }

     override suspend fun findByUserName(userName: String): User? {
         println("this is a thing")
        return dbQuery {
            UserTable.select { UserTable.name eq userName }
                .map { rowToUser(it) }
                .singleOrNull()
        }
    }



    private fun rowToUser(row: ResultRow): User {
        return User(
            id = row[UserTable.id],
            name = row[UserTable.name],
            bio = row[UserTable.bio],
            avatar = row[UserTable.avatar],
            password = row[UserTable.password]
        )
    }
}


