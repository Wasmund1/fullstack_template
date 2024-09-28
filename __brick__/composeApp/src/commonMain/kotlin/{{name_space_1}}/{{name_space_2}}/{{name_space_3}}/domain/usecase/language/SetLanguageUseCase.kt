package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.domain.usecase.language

import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.utils.Language
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.utils.changeLang

abstract class SetLanguageUseCase {
    abstract operator fun invoke(language: Language)
}

class SetLanguageUseCaseImpl : SetLanguageUseCase() {
    override fun invoke(language: Language) {
        changeLang(language.shortName)
    }
}
