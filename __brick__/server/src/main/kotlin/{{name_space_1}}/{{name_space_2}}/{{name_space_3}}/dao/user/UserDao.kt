package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.dao.user

import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.model.SignUpParams
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.model.User

interface UserDao {
    suspend fun insert(params: SignUpParams): User?
    suspend fun findByEmail(email: String): User?
    suspend fun findByUserName(userName: String): User?
}