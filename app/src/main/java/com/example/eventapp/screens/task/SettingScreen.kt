package com.example.eventapp.screens.task

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.eventapp.R
import com.example.eventapp.component.TasksHeaderView
import com.example.eventapp.data.entity.TagWithTaskLists

@Composable
fun SettingScreen(navController: NavHostController) {


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
    ) {


        Image(
            painter = painterResource(id = R.drawable.background_image),
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.5f),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.padding(top = 30.dp))



        Column(modifier = Modifier.padding(16.dp)) {

            TasksHeaderView("Setting") {
                navController.popBackStack()
            }



            Text(
                text = "General",
                fontSize = 25.sp,
                color = Color.White
            )




            Box(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(Color.White)
                    .fillMaxWidth()
            )
            {
                Row(horizontalArrangement = Arrangement.Center) {

                    TextButton(
                        onClick = { /*TODO*/ }
                    )
                    {
                        Text(
                            text = "Language",
                            fontSize = 20.sp
                        )
                    }

                    Spacer(modifier = Modifier.width(215.dp))


                    Icon(
                        Icons.Outlined.ArrowForward,
                        contentDescription = "Arrow Right",
                        tint = Color(0xFF8A7488),
                        modifier = Modifier.size(40.dp).padding(top = 10.dp)

                    )

                }

            }


            Spacer(modifier = Modifier.padding(top = 16.dp))







            Box(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(Color.White)
                    .fillMaxWidth()
            )
            {
                Row(horizontalArrangement = Arrangement.Center) {

                    TextButton(
                        onClick = { /*TODO*/ }
                    )
                    {
                        Text(
                            text = "Delete Account",
                            fontSize = 20.sp
                        )
                    }

                    Spacer(modifier = Modifier.width(168.dp))


                    Icon(
                        Icons.Outlined.Delete,
                        contentDescription = "Delete",
                        tint = Color(0xFF8A7488),
                        modifier = Modifier.size(40.dp).padding(top = 10.dp)

                    )

                }

            }


        }
    }
}

