package com.example.streemapp.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.streemapp.R
import com.example.streemapp.SampleViewModel

@Composable
fun RegisterScreen(
    onRegisterClick: () -> Unit,
    onLoginClick: () -> Unit,
    viewModel: SampleViewModel // Your ViewModel
) {
    
    var number by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var retypePassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var passwordVisibleRetype by remember { mutableStateOf(false) }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){ Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.user),
            contentDescription = "login icon",
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
                .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )

        Spacer(modifier = Modifier.height(8.dp))
        TextButton(
            onClick = onLoginClick,
        ) {
            Text(
                text = "Upload Image",
                style = MaterialTheme.typography.titleMedium,
                color = Color(17, 120, 36)
            )
        }

    }

        
        Text(
            text = "Register Account",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom =  32.dp)
        )
        OutlinedTextField(
            value = number,
            onValueChange = {

                if(it.all { char -> char.isDigit() }){
                    number =it
                }

            },
            label = { Text("Phonenumber") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true
        )

        OutlinedTextField(
            value = address,
            onValueChange = { address = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),

            label = { Text("Detail Address") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            singleLine = false
        )



        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            singleLine = true,
            trailingIcon = {
                val image = if (passwordVisible)
                    painterResource(id = R.drawable.view)
                else
                    painterResource(id = R.drawable.hide)

                IconButton(onClick = {
                    passwordVisible = !passwordVisible
                }) {
                    Icon(painter = image, contentDescription = if (passwordVisible) "Hide password" else "Show password")
                }
            }
        )
        OutlinedTextField(
            value = retypePassword,
            onValueChange = { retypePassword = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (passwordVisibleRetype) VisualTransformation.None else PasswordVisualTransformation(),
            singleLine = true,
            trailingIcon = {
                val image = if (passwordVisibleRetype)
                    painterResource(id = R.drawable.view)
                else
                    painterResource(id = R.drawable.hide)

                IconButton(onClick = {
                    passwordVisibleRetype = !passwordVisibleRetype
                }) {
                    Icon(painter = image, contentDescription = if (passwordVisibleRetype) "Hide password" else "Show password")
                }
            }
        )
        Button(
            onClick = {
                // Handle Register click
                viewModel.register(number,username,password)
                onLoginClick()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Register your Account")
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(
            onClick = onLoginClick,
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Have an account? Loing")
        }





    }
    
}
