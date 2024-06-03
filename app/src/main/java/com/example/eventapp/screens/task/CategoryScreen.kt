package com.example.eventapp.screens.task

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.eventapp.R
import com.example.eventapp.component.TagCard
//import com.example.eventapp.component.TagCard
import com.example.eventapp.component.UserImageWithEmail
import com.example.eventapp.iconByName
import com.example.eventapp.navigation.Screens
import com.example.eventapp.ui.theme.PrimaryColor
import com.google.firebase.auth.FirebaseUser

@Composable
fun CategoryScreen(
    user: FirebaseUser?,
    viewModel: TaskViewModel,
    navController: NavHostController,
    logout: () -> Unit,
) {
    val tagsWithTasksList = viewModel.tagWithTasks

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black),) {

        Image(
            painter = painterResource(id = R.drawable.background_image),
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.4f),
            contentScale = ContentScale.Crop
        )

        Column {
            UserImageWithEmail(user = user, navController)
            Column {
                LazyVerticalGrid(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 90.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    columns = GridCells.Fixed(2)
                ) {
                    items(tagsWithTasksList.value) {
                        TagCard(
                            Color(it.tag.color.toIntOrNull() ?: PrimaryColor.toArgb()),
                            iconByName(it.tag.iconName),
                            it.tag.name,
                            "${it.tasks.size} Task"
                        ) {
                            navController.navigate("${Screens.MainApp.TaskByCategory.route}/${it.tag.name}")
                        }
                    }
                }

            }
        }
    }
}

//@Composable
//fun cardBox(){
//
//    Box(modifier = Modifier
//        .height(25.dp)
//        .width(30.dp)
//    ){
//        Column {
//            Row {
//                Icon(imageVector = , contentDescription = )
//                Text(text = "Stting")
//        }
//
//            Row {
//                Icon(imageVector = , contentDescription = )
//                Text(text = "Stting")
//            }
//
//    }
//
//}

