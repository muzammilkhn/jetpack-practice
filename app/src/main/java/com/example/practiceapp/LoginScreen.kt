package com.example.practiceapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen() {
    var emailValue by remember { mutableStateOf("") }
    var passwordValue by remember { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(20.dp))
        TextFieldWidget("Email", emailValue) { emailValue = it }
        Spacer(modifier = Modifier.height(20.dp))
        TextFieldWidget("Password", passwordValue) { passwordValue = it }
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { /*TODO*/ }) {
            Text("abcd")

        }
    }
}

@Composable
fun TextFieldWidget(
    label: String,
    value: String,
    onChange: (value: String) -> Unit
) {
    Column {
        Text(label)
        OutlinedTextField(
            value = value,
            onValueChange = { onChange(it) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Color.Green,
                focusedBorderColor = Color.Green,
                unfocusedBorderColor = Color.Gray
            ),
        )
    }

}