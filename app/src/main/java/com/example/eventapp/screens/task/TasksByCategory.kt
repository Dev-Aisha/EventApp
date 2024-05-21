package com.example.eventapp.screens.task

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.eventapp.R
import com.example.eventapp.component.TaskCard
import com.example.eventapp.component.TasksHeaderView
import com.example.eventapp.data.entity.TagWithTaskLists

@Composable
fun TasksByCategory(tagWithTaskLists: TagWithTaskLists?, navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize().background(Color.Black),) {

        Image(
            painter = painterResource(id = R.drawable.background_image),
            contentDescription = "",
            modifier = Modifier.fillMaxSize().alpha(0.5f),
            contentScale = ContentScale.Crop
        )

        Column(modifier = Modifier.padding(16.dp)) {
            TasksHeaderView(tagWithTaskLists?.tag?.name.orEmpty()) {
                navController.popBackStack()
            }
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(tagWithTaskLists?.tasks.orEmpty()) {

                    TaskCard(
                        taskTitle = it.title,
                        it.timeFrom,
                        timeTo = it.timeTo,
                        tag = listOf(tagWithTaskLists?.tag)
                    )
                }
            }
        }
    }
}