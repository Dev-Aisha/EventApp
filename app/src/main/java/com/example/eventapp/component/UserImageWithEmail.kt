package com.example.eventapp.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.eventapp.R
import com.example.eventapp.ui.theme.Navy
import com.google.firebase.auth.FirebaseUser
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavHostController
import com.example.eventapp.navigation.Screens


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserImageWithEmail(user: FirebaseUser?, navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(22.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            var expanded by remember { mutableStateOf(false) }




            Box(contentAlignment = Alignment.TopEnd) {
                Card(
                    modifier = Modifier
                        .size(64.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 16.dp
                    ),
                    onClick = { expanded = true } // Toggle the menu on click
                ) {
                    Icon(
                        Icons.Outlined.Menu,
                        contentDescription = "Menu",
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .fillMaxSize()
                            .padding(16.dp),
                    )
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    properties = PopupProperties(clippingEnabled = false),
//                    offset = DpOffset(0.dp, 64.dp)
                ) {
                    DropdownMenuItem(

                        { Text("Setting") },
                        leadingIcon = {
                            Icon(
                                Icons.Outlined.Settings,
                                contentDescription = null
                            )
                        },
                        onClick = {
                            expanded = false
                            navController.navigate(Screens.MainApp.Setting.route)
                        }
                    )
                    DropdownMenuItem(
                        { Text("Log out") },
                        leadingIcon = {
                            Icon(
                                Icons.Outlined.ExitToApp,
                                contentDescription = null
                            )
                        },
                        onClick =
                        {
                            expanded = false
                            navController.navigate(Screens.Authentication.Login.route)
                        }
                    )
                }
            }
        }

        Card(
            modifier = Modifier.size(64.dp),
            shape = RoundedCornerShape(50),//use 20 if you want to round corners like the one in the design
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 16.dp
            ),

            ) {

            if (user?.photoUrl.toString().isEmpty()) {
                Image(
                    painter = painterResource(id = R.drawable.user_avatar_male),
                    contentDescription = "profile picture",
                    modifier = Modifier.size(64.dp),
                    contentScale = ContentScale.Crop
                )
            } else {
                AsyncImage(
                    model = user?.photoUrl,
                    contentDescription = "profile picture",
                    modifier = Modifier.size(64.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }


        Text(
            user?.displayName.orEmpty(),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Navy
        )
        Text(
            user?.email.orEmpty(),
            modifier = Modifier.padding(vertical = 8.dp),
            fontSize = 14.sp,
            color = Color.DarkGray
        )
    }
}


//@Preview
//@Composable
//fun UserImageWithEmailPreview() {
//    UserImageWithEmail(null)
//}