package com.example.eventapp.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.eventapp.R
import com.example.eventapp.component.LoginWithGoogle
import com.example.eventapp.navigation.Screens
import com.example.eventapp.ui.theme.PrimaryColor
import com.google.android.gms.auth.api.signin.GoogleSignIn


@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalComposeUiApi
@Composable
fun SignUpScreen(navController: NavHostController, authViewModel: AuthViewModel) {
    val emailState = remember { mutableStateOf(TextFieldValue()) }
    val passwordState = remember { mutableStateOf(TextFieldValue()) }
    val userName = remember { mutableStateOf(TextFieldValue()) }

    Text(
        modifier = Modifier.padding(vertical = 50.dp, horizontal = 16.dp),
        text = "Sign Up",
        color = PrimaryColor,
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            leadingIcon = { Icon(Icons.Outlined.Person, contentDescription = "") },
            modifier = Modifier.fillMaxWidth(0.9f),
            value = userName.value,
            onValueChange = { userName.value = it },
            label = { Text("User Name") },
            colors = TextFieldDefaults.colors(
                disabledContainerColor = Color.White.copy(0.5f),

            )
        )
        TextField(
            leadingIcon = { Icon(Icons.Outlined.Email, contentDescription = "") },
            modifier = Modifier.fillMaxWidth(0.9f),
            value = emailState.value,
            onValueChange = { emailState.value = it },
            label = { Text("Email") },
            colors = TextFieldDefaults.colors(
                disabledContainerColor = Color.White.copy(0.5f),
                focusedContainerColor = Color.White,

            )
        )
        TextField(
            leadingIcon = {
                Icon(Icons.Outlined.Lock, contentDescription = "") },
            modifier = Modifier.fillMaxWidth(0.9f),
            value = passwordState.value,
            onValueChange = { passwordState.value = it },
            label = { Text("Password") },
            colors = TextFieldDefaults.colors(
                focusedTextColor= PrimaryColor

            ),
            visualTransformation = PasswordVisualTransformation()
        )



        Button(
            onClick = {
                    authViewModel.signup(emailState.value.text.trim(), passwordState.value.text.trim())
            },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(vertical = 50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryColor
            )
        ) {
            Text(
                modifier = Modifier.padding(vertical = 8.dp), text = "Create Account",
                fontSize = 16.sp,
                color = Color.White
            )

        }
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Divider(color = Color.Gray.copy(0.2f))
            Text(
                modifier = Modifier
                    .background((Color.White))
                    .padding(vertical = 8.dp), text = "---Or With---",
                fontSize = 16.sp,
                color = Color.Gray.copy(0.8f)
            )
        }

        LoginWithGoogle(authViewModel= authViewModel)

    }
}










































//
//@Composable
//fun SignUpScrreen(navController: NavHostController, authViewModel: AuthViewModel) {
//
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(color = Color.White),
//            verticalArrangement = Arrangement.Center,
//        ) {
//
//            Text(
//                text = "Sign Up",
//                color = Color(0xff5b67ca),
//                style = MaterialTheme.typography.displaySmall,
//                modifier = Modifier.padding(5.dp))
//
//            Row {
//
//                Image(
//                    painter = painterResource(id = R.drawable.message_auth),
//                    contentDescription = "email id",
//                    modifier = Modifier.size(24.dp))
//                TextField(
//                    value = "",
//                    onValueChange = {},
//                    colors = TextFieldDefaults.textFieldColors(
//                        containerColor = Color(0xffc6cedd)
//                    ),
//                    modifier = Modifier)
//
//            }
//
//            Row {
//
//                Image(
//                    painter = painterResource(id = R.drawable.lock),
//                    contentDescription = "Iconly/Curved/Lock",
//                    modifier = Modifier.size(24.dp)
//                        )
//
//                TextField(
//                    value = "",
//                    onValueChange = {},
//                    colors = TextFieldDefaults.textFieldColors(
//                        containerColor = Color(0xffc6cedd)
//                    ),
//                    modifier = Modifier
//                )
//                Image(
//                    painter = painterResource(id = R.drawable.hide_auth),
//                    contentDescription = "Iconly/Curved/Hide",
//                    modifier = Modifier.size(20.dp)
//                )
//
//            }
//
//            Row {
//
//                Image(
//                    painter = painterResource(id = R.drawable.lock),
//                    contentDescription = "Iconly/Curved/Lock",
//                    modifier =  Modifier.size(20.dp)
//                )
//                TextField(
//                    value = "",
//                    onValueChange = {},
//                    colors = TextFieldDefaults.textFieldColors(
//                        containerColor = Color(0xffc6cedd)
//                    ),
//                    modifier = Modifier
//                )
//                Image(
//                    painter = painterResource(id = R.drawable.hide_auth),
//                    contentDescription = "Iconly/Curved/Hide",
//                    modifier =  Modifier.size(20.dp)
//                )
//
//            }
//
//
//            Button(
//                onClick = { },
//                shape = RoundedCornerShape(14.dp),
//                colors = ButtonDefaults.buttonColors(containerColor = Color(0xff5b67ca)),
//                modifier = Modifier
//                    .offset(
//                        x = 36.dp,
//                        y = 433.dp
//                    )
//                    .requiredWidth(width = 304.dp)
//                    .requiredHeight(height = 52.dp))
//            {
//                Text(
//                    text = "Create",
//                    color = Color(0xfffafafa),
//                    lineHeight = 1.06.em,
//                    style = TextStyle(
//                        fontSize = 16.sp),
//                    modifier = Modifier)
//            }
//
//            Text(
//                text = "or with",
//                color = Color(0xffc6cedd).copy(alpha = 0.8f),
//                lineHeight = 1.21.em,
//                style = TextStyle(
//                    fontSize = 14.sp),
//                modifier = Modifier
//                    .offset(x = 170.dp,
//                        y = 549.dp))
//            Image(
//                painter = painterResource(id = R.drawable.google_image),
//                contentDescription = "Group 732",
//                modifier = Modifier
//                    .offset(
//                        x = 174.dp,
//                        y = 610.dp
//                    )
//                    .requiredSize(size = 42.dp))
//            Text(
//                lineHeight = 1.sp,
//                text = buildAnnotatedString {
//                    withStyle(style = SpanStyle(
//                        color = Color(0xff2c406e),
//                        fontSize = 14.sp)
//                    ) {append("Have any account?")}
//                    withStyle(style = SpanStyle(
//                        color = Color(0xff2c406e),
//                        fontSize = 14.sp)
//                    ) {append("Sign In")}},
//                modifier = Modifier
//                    .offset(x = 99.dp,
//                        y = 751.dp))
//        }
//    }