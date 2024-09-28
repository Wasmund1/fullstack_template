package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.utils

import platform.Foundation.NSUserDefaults

actual fun changeLang(lang: String) {
    NSUserDefaults.standardUserDefaults.setObject(arrayListOf(lang), "AppleLanguages")
}
