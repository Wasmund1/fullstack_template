package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.utils

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable

typealias columnScope = @Composable ColumnScope.() -> Unit
typealias rowScope = @Composable RowScope.() -> Unit
