package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.utils

import java.util.Locale

actual fun changeLang(lang: String) {
    val locale = Locale(lang)
    Locale.setDefault(locale)
}
