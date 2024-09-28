package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.presentation.ui.components.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(bottom = 12.dp, top = 12.dp),
        onClick = onClick,
        enabled = enabled,
        content = @Composable {
            Text(
                text,
                style = MaterialTheme.typography.bodyLarge,
            )
        },
    )
}
