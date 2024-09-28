package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.presentation.ui.components.textfield

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    initialValue: String = "",
    label: String,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable (() -> Unit)? = null,
    onValueChange: (String) -> Unit,
) {
    val interactionSource =
        remember {
            MutableInteractionSource()
        }
    var currentValue by remember { mutableStateOf(initialValue) }
    BasicTextField(
        textStyle = TextStyle(fontSize = 18.sp),
        modifier = Modifier.fillMaxWidth(),
        value = currentValue,
        visualTransformation = visualTransformation,
        interactionSource = interactionSource,
        enabled = enabled,
        singleLine = singleLine,
        onValueChange = { value ->
            currentValue = value
            onValueChange(value)
        },
    ) {
        TextFieldDefaults.DecorationBox(
            enabled = enabled,
            singleLine = singleLine,
            innerTextField = it,
            interactionSource = interactionSource,
            value = currentValue,
            label = @Composable { Text(label) },
            visualTransformation = visualTransformation,
            trailingIcon = trailingIcon,
            colors =
                TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                ),
        )
    }
}
