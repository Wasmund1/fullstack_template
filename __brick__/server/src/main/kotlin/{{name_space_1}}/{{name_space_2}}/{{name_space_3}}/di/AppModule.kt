package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.di

import org.koin.dsl.module
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.dao.auth.RefreshTokenDao
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.dao.auth.RefreshTokenDaoImpl
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.dao.user.UserDao
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.dao.user.UserDaoImpl
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.repository.auth.AuthRepository
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.repository.auth.AuthRepositoryImpl

val appModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    single<UserDao> { UserDaoImpl() }
    single<RefreshTokenDao> { RefreshTokenDaoImpl() }
}