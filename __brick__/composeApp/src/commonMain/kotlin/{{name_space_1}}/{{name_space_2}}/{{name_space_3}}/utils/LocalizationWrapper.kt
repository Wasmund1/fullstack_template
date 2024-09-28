package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.utils

expect fun changeLang(lang: String)

sealed class Language {
    abstract val name: String
    abstract val shortName: String

    class Italian(override val name: String = "Italiano", override val shortName: String = "it") :
        Language()

    class German(override val name: String = "Deutsch", override val shortName: String = "de") :
        Language()

    class English(override val name: String = "English", override val shortName: String = "en") :
        Language()
}
