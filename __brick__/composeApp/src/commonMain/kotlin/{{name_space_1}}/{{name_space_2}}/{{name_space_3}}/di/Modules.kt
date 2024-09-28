package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.di


import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.data.repository.AuthRepositoryImpl
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.domain.repository.AuthRepository
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.domain.usecase.auth.LoginUseCase
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.domain.usecase.auth.LoginUseCaseImpl
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.domain.usecase.auth.LoginWithTokenUseCase
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.domain.usecase.auth.LoginWithTokenUseCaseImpl
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.domain.usecase.auth.SignUpUseCase
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.domain.usecase.auth.SignUpUseCaseImpl
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.domain.usecase.language.SetLanguageUseCase
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.domain.usecase.language.SetLanguageUseCaseImpl
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.data.AuthService
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.data.AuthServiceImpl
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.data.TokenService
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.data.TokenServiceImpl
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.domain.usecase.localstorage.ReadStringFromDataStoreUseCase
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.domain.usecase.localstorage.ReadStringFromDataStoreUseCaseImpl
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.domain.usecase.localstorage.WriteStringToDataStoreUseCase
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.domain.usecase.localstorage.WriteStringToDataStoreUseCaseImpl
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.presentation.auth.login.DefaultLoginViewModel
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.presentation.auth.login.LoginViewModel
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.presentation.auth.signUp.DefaultSignUpViewModel
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.presentation.auth.signUp.SignupViewModel
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.presentation.auth.welcome.DefaultWelcomeViewModel
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.presentation.auth.welcome.WelcomeViewModel
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.presentation.home.DefaultHomeViewModel
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.presentation.home.HomeViewModel

val presentationModule =
    module {
        viewModel<SignupViewModel> { DefaultSignUpViewModel(get(), get()) }
        viewModel<LoginViewModel> { DefaultLoginViewModel(get(), get()) }
        viewModel<WelcomeViewModel> { DefaultWelcomeViewModel(get(), get()) }
        viewModelOf<HomeViewModel>(::DefaultHomeViewModel)
    }

val domainModule =
    module {
        factory<SignUpUseCase> { SignUpUseCaseImpl(get()) }
        factory<LoginUseCase> { LoginUseCaseImpl(get()) }
        factory<LoginWithTokenUseCase> { LoginWithTokenUseCaseImpl(get()) }
        factory<ReadStringFromDataStoreUseCase> { ReadStringFromDataStoreUseCaseImpl(get()) }
        factory<WriteStringToDataStoreUseCase> { WriteStringToDataStoreUseCaseImpl(get()) }
        factory<SetLanguageUseCase> { SetLanguageUseCaseImpl() }
    }

val dataModule =
    module {
        single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
        single<AuthService> {AuthServiceImpl() }
        single<TokenService> { TokenServiceImpl(get(), get()) }
    }

expect val platformModule: Module
