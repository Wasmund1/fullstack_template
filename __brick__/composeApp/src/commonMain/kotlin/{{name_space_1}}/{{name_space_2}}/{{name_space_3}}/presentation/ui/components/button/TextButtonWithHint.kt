package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.presentation.ui.components.button

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@Composable
fun TextButtonWithHint(
    hintText: String,
    buttonText: String = "Click Here",
    onClick: () -> Unit,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(hintText)
        TextButton(onClick = onClick, content = { Text(buttonText) })
    }
}
