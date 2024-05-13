package com.example.eventapp.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.eventapp.R
import com.example.eventapp.component.LoginWithGoogle
import com.example.eventapp.navigation.Screens
import com.example.eventapp.ui.theme.PrimaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController, authViewModel: AuthViewModel) {

    val emailState = remember { mutableStateOf(TextFieldValue()) }
    val passwordState = remember { mutableStateOf(TextFieldValue()) }

    Text(
        modifier = Modifier.padding(vertical = 50.dp, horizontal = 16.dp),
        text = "Login",
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
            leadingIcon = {
                Icon(Icons.Outlined.Email, contentDescription = "")
                          },
            modifier = Modifier.fillMaxWidth(0.9f),
            value = emailState.value,
            onValueChange = { emailState.value = it },
            label = { Text("Email") },
            colors = TextFieldDefaults.colors(
                disabledContainerColor = Color.White.copy(0.5f),


            )
        )
        TextField(
            leadingIcon = {
                Icon(Icons.Outlined.Lock, contentDescription = "")
                          },
            modifier = Modifier.fillMaxWidth(0.9f),
            value = passwordState.value,
            onValueChange = { passwordState.value = it },
            label = { Text("Password") },
            colors = TextFieldDefaults.colors(
                disabledContainerColor = Color.White.copy(0.5f),
            ),
            visualTransformation = PasswordVisualTransformation()
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
                .clickable {
                    authViewModel.resetPassword(emailState.value.text)
                },
            text = "Forgot Password?",
            color = PrimaryColor,
            fontSize = 12.sp,
            textAlign = TextAlign.End
        )

        Button(
            onClick = {
                    authViewModel.login(emailState.value.text, passwordState.value.text)

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
                modifier = Modifier.padding(vertical = 8.dp), text = "Login",
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








































//        Column(
//            modifier = Modifier.fillMaxSize()
//                .background(color = Color.White),
//            verticalArrangement = Arrangement.Center,
//        ) {
//
//            Text(
//                text = "Login",
//                color = Color(0xff5b67ca),
//                style = MaterialTheme.typography.displaySmall,
//                modifier = Modifier.padding(5.dp))
//
//            Image(
//                painter = painterResource(id = R.drawable.message_auth),
//                contentDescription = "email id",
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(start = 23.dp,
//                        end = 328.dp,
//                        top = 227.dp,
//                        bottom = 561.dp))
//            TextField(
//                value = "",
//                onValueChange = {},
//                colors = TextFieldDefaults.textFieldColors(
//                    containerColor = Color(0xffc6cedd)),
//                modifier = Modifier
//                    .offset(x = 56.dp,
//                        y = 231.dp))
//            Image(
//                painter = painterResource(id = R.drawable.lock),
//                contentDescription = "Iconly/Curved/Lock",
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(start = 23.dp,
//                        end = 328.dp,
//                        top = 283.dp,
//                        bottom = 505.dp))
//            Image(
//                painter = painterResource(id = R.drawable.hide_auth),
//                contentDescription = "Iconly/Curved/Hide",
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(start = 331.dp,
//                        end = 28.dp,
//                        top = 283.dp,
//                        bottom = 513.dp))
//            TextField(
//                value = "",
//                onValueChange = {},
//                colors = TextFieldDefaults.textFieldColors(
//                    containerColor = Color(0xffc6cedd)),
//                modifier = Modifier
//                    .offset(x = 56.dp,
//                        y = 288.dp)
//            )
//
//            Text(
//                text = "Forgot Password ?",
//                color = Color(0xff5b67ca),
//                lineHeight = 1.42.em,
//                style = TextStyle(
//                    fontSize = 12.sp),
//                modifier = Modifier
//                    .offset(x = 244.dp,
//                        y = 359.dp)
//            )
//
//            Button(
//                onClick = { },
//                shape = RoundedCornerShape(14.dp),
//                colors = ButtonDefaults.buttonColors(containerColor = Color(0xff5b67ca)),
//                modifier = Modifier
//                    .offset(x = 36.dp,
//                        y = 433.dp)
//                    .requiredWidth(width = 304.dp)
//                    .requiredHeight(height = 52.dp)){
//                Text(
//                    text = "Login",
//                    color = Color(0xfffafafa),
//                    lineHeight = 1.06.em,
//                    style = TextStyle(
//                        fontSize = 16.sp),
//                    modifier = Modifier
//                        .offset(x = 165.dp,
//                            y = 450.dp))
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
//                    .offset(x = 174.dp,
//                        y = 610.dp)
//                    .requiredSize(size = 42.dp))
//            Text(
//                lineHeight = 1.sp,
//                text = buildAnnotatedString {
//                    withStyle(style = SpanStyle(
//                        color = Color(0xff2c406e),
//                        fontSize = 14.sp)) {append("Don’t have an account? ")}
//                    withStyle(style = SpanStyle(
//                        color = Color(0xff2c406e),
//                        fontSize = 14.sp)) {append("Sign Up")}},
//                modifier = Modifier
//                    .offset(x = 99.dp,
//                        y = 751.dp))
//        }
//    }
