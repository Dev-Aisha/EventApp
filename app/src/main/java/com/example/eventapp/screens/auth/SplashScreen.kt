package com.example.eventapp.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.eventapp.R
import com.example.eventapp.navigation.Screens
import com.example.eventapp.ui.theme.PrimaryColor


@Composable
fun SplashScreen(navController: NavHostController) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.spalsh_screen),
            contentDescription = "",
            modifier = Modifier.padding(horizontal = 22.dp).size(300.dp)

        )
        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = "Event App",
            color = PrimaryColor,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        )
        Text(
            modifier = Modifier.padding(horizontal = 22.dp),
            textAlign = TextAlign.Center,
            text = "Plan what you will do to be more organized for today, tomorrow and beyond",
        )

        Spacer(modifier = Modifier.height(50.dp))
        Button(
            onClick = {
                navController.navigate(Screens.Authentication.Login.route)
            },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(12.dp),
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
        Text(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .clickable {
                    navController.navigate(Screens.Authentication.SignUp.route)

                },
            text = "Sign Up",
            color = PrimaryColor,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
        )
    }
}
























































//
//    Column(
//        modifier = Modifier.fillMaxSize()
//            .background(color = Color.White),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Image(
//            painter = painterResource(id = R.drawable.spalsh_screen),
//            contentDescription = "spalsh_screen",
//            modifier = Modifier
//                .requiredWidth(width = 291.dp)
//                .requiredHeight(height = 296.dp)
//        )
//        Text(
//            text = "Dailoz",
//            color = Color(0xff5b67ca),
//            style = TextStyle(
//                fontSize = 32.sp,
//                fontWeight = FontWeight.Bold
//            ),
//        )
//        Text(
//            text = "Plan what you will do to be more organized for today, tomorrow and beyond",
//            color = Color(0xff2c406e),
//            lineHeight = 1.21.em,
//            style = TextStyle(
//                fontSize = 14.sp
//            ),
//            modifier = Modifier
//                .requiredWidth(width = 323.dp)
//        )
//        Spacer(modifier = Modifier.padding(50.dp))
//
//        Button(
//            onClick = { navController.navigate(Screens.Authentication.Login.route) },
//            Modifier
//                .fillMaxWidth()
//                .height(45.dp),
//            colors = ButtonDefaults.buttonColors(Color(0xff2c406e)),
//            shape = RoundedCornerShape(18.dp)
//        ) {
//            Text(text = "Login", fontSize = 20.sp)
//        }
//        Box(
//            modifier = Modifier
//                .fillMaxWidth(.9f)
//                .height(80.dp)
//                .padding(22.dp),
//            Alignment.Center
//        ) {
//            Text(text = "Sign up",
//                Modifier.clickable {
//                    navController.navigate(Screens.Authentication.SignUp.route)
//                })
//        }
//    }
//}
