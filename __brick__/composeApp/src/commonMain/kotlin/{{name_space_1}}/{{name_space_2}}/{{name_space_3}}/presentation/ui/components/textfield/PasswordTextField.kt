package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.presentation.ui.components.textfield

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun PasswordTextField(
    label: String,
    onValueChange: (String) -> Unit,
) {
    var isPasswordVisible by remember { mutableStateOf(false) }
    CustomTextField(
        label = label,
        onValueChange = onValueChange,
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(
                onClick = { isPasswordVisible = !isPasswordVisible },
                content = {
                    Icon(
                        imageVector = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = "hallo",
                    )
                },
            )
        },
    )
}
