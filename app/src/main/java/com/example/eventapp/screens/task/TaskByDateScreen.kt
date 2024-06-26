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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.eventapp.R
import com.example.eventapp.component.CalendarWeeklyView
import com.example.eventapp.component.TaskCard
import java.time.LocalDate

@Composable
fun TaskByDateScreen(viewmodel: TaskViewModel, navController: NavHostController) {
    val tasks = viewmodel.taskWithTags

    LaunchedEffect(Unit) {
        viewmodel.sortTasksByDate(LocalDate.now().toString())
    }
    var selectedDate by remember {
        mutableStateOf(LocalDate.now().toString())
    }
    Box(modifier = Modifier.fillMaxSize().background(Color.Black),) {
        Image(
            painter = painterResource(id = R.drawable.background_image),
            contentDescription = "",
            modifier = Modifier.fillMaxSize().alpha(0.3f),
            contentScale = ContentScale.Crop
        )
            Column {

                CalendarWeeklyView(onDateSelected = {
                    viewmodel.sortTasksByDate(it.toString())
                    selectedDate = it.toString()
                }
                )

                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Text(
                        text = if (selectedDate == LocalDate.now()
                                .toString()
                        ) "Today" else selectedDate,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = "09 h 45 min")
                }
                LazyColumn(
                    modifier = Modifier
                        .padding(16.dp)
                        .padding(bottom = 100.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    val groupedList = tasks.value.groupBy {
                        it.task.timeFrom
                    }
                    if (groupedList.isNotEmpty()) {
                        groupedList.forEach {
                            item {
                                Divider()
                            }
                            item {
                                LazyRow(
                                    modifier = Modifier.fillParentMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                                ) {
                                    item {
                                        Text(text = it.key.orEmpty(),color = Color.White)
                                    }
                                    items(it.value) {
                                        taskWithTags ->
                                        Box(modifier = Modifier.fillParentMaxWidth(0.6f)) {
                                            TaskCard(
                                                taskTitle = taskWithTags.task.title,
                                                taskWithTags.task.timeFrom,
                                                taskWithTags.task.timeTo,
                                                taskWithTags.tags,
                                                task = taskWithTags.task,
                                                viewModel = viewmodel,
                                                navController = navController

                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                    else {
                        item {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_empty_view),
                                    contentDescription = ""
                                )
                                Text(
                                    modifier = Modifier.padding(vertical = 16.dp),
                                    text = "You don’t have any schedule today.\n" +
                                            "Tap the plus button to create new to-do.",
                                    color = Color.White,
                                    textAlign = TextAlign.Center
                                )
                            }

                        }
                    }

                }
            }
        }
    }