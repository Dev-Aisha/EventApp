package com.example.eventapp.screens.task

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.eventapp.R
import com.example.eventapp.component.AddTagsListView
import com.example.eventapp.component.CustomTextField
import com.example.eventapp.component.TasksHeaderView
import com.example.eventapp.component.TimePickerDialog
import com.example.eventapp.component.TimeView
import com.example.eventapp.navigation.Screens
import com.example.eventapp.ui.theme.PrimaryColor

@Composable
fun AddTaskScreen(navController: NavHostController, viewModel: AddTaskViewModel) {
    val allTags = viewModel.allTags.collectAsState(initial = null)
    val showStartTimeTimeDialog = remember {
        mutableStateOf(false)
    }

    val showEndTimeTimeDialog = remember {
        mutableStateOf(false)
    }
    Box(modifier = Modifier.fillMaxSize().background(Color.Black),) {


        Image(
            painter = painterResource(id = R.drawable.background_image),
            contentDescription = "",
            modifier = Modifier.fillMaxSize().alpha(0.5f),
            contentScale = ContentScale.Crop
        )


        LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
            item {
                //  header
                TasksHeaderView("Add Task") {
                    navController.popBackStack()
                }
            }
            //task fields
            item {

                CustomTextField(Modifier, "Title", Color.Gray, viewModel.title)

                CustomTextField(
                    Modifier,
                    "Date",
                    Color.Gray,
                    viewModel.taskDate,
                    isReadOnly = true,
                    trailingIcon = {
                        Icon(imageVector = Icons.Outlined.DateRange, "", modifier =
                        Modifier.clickable {
                            navController.navigate(Screens.MainApp.DateDialog.route)
                        })
                    })
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    val context = LocalContext.current
                    TimeView(context, viewModel.startTime, viewModel.endTime)
//                CustomTextField(Modifier.weight(1f), "Time From", Color.Gray, viewModel.startTime, trailingIcon = {
//                    Icon(imageVector = Icons.Outlined.Notifications, "", modifier =
//                    Modifier.clickable {
//                        navController.navigate(Screens.MainApp.TimeDialog.route)
//                    }
//                    )
//
//                }
//                )
//                    TimeView(context, viewModel.startTime, viewModel.endTime)

//                CustomTextField(Modifier.weight(1f), "Time To", Color.Gray, viewModel.endTime, trailingIcon = {
//                    Icon(imageVector = Icons.Outlined.Notifications, "", modifier =
//                    Modifier.clickable {
//                        navController.navigate(Screens.MainApp.TimeDialog.route)
//                    }
//                    )
//
//                }
//                )
                }
                CustomTextField(Modifier, "Description", Color.Gray, viewModel.description)

            }
            //tags List
            item {
                AddTagsListView(allTags.value.orEmpty(), navController) {
                    viewModel.selectedTags.value = it
                }
            }

            item {
                //add task button
                ButtonAddTask(viewModel)
            }
        }
    }
}


    @Composable
    fun ButtonAddTask(addTask: AddTaskViewModel) {
        Button(
            onClick = {
                addTask.addTask()
            },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(22.dp)
                .padding(bottom = 100.dp)
                .semantics {
                    testTag = "Add Task Button"
                },
            colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryColor
            )
        ) {
            Text(
                modifier = Modifier.padding(vertical = 8.dp), text = "Create",
                fontSize = 16.sp,
                color = Color.White
            )

        }
    }
