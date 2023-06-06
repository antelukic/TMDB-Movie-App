package com.lukic.movieapp.ui.components

import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.lukic.movieapp.R

@Composable
fun MovieTextField(
    text: String,
    onSearchTextChange: (String) -> Unit,
    onClearValue: () -> Unit,
    modifier: Modifier = Modifier,
    placeholderText: String = "",
    placeHolderTextColor: Color = MaterialTheme.colors.primary,
    labelTextStyle: TextStyle = MaterialTheme.typography.body1,
    backgroundColor: Color = MaterialTheme.colors.secondary,
    textColor: Color = MaterialTheme.colors.onSecondary,
    cursorColor: Color = MaterialTheme.colors.primary,
    focusedIndicatorColor: Color = Color.Transparent,
    unfocusedIndicatorColor: Color = Color.Transparent,
    disabledIndicatorColor: Color = Color.Transparent
) {
    TextField(
        value = text,
        onValueChange = onSearchTextChange,
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = null)
        },
        trailingIcon = {
            if (text.isNotEmpty()) {
                IconButton(onClick = { onClearValue() }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(R.string.clear_search_content_description)
                    )
                }
            }
        },
        placeholder = {
            Text(
                text = placeholderText,
                color = placeHolderTextColor,
                style = labelTextStyle
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = backgroundColor,
            textColor = textColor,
            cursorColor = cursorColor,
            focusedIndicatorColor = focusedIndicatorColor,
            unfocusedIndicatorColor = unfocusedIndicatorColor,
            disabledIndicatorColor = disabledIndicatorColor
        ),
        singleLine = true,
        modifier = modifier.heightIn(48.dp)
    )
}
