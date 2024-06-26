package com.example.eventapp.screens.task

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.eventapp.R
import com.example.eventapp.component.AddTagsListView
import com.example.eventapp.component.CustomTextField
import com.example.eventapp.component.TimeView
import com.example.eventapp.navigation.Screens
import com.example.eventapp.ui.theme.Navy
import com.example.eventapp.ui.theme.PrimaryColor

@Composable
fun AddTaskScreen(navController: NavHostController, viewModel: AddTaskViewModel) {

    val allTags = viewModel.allTags.collectAsState()

    Box(modifier = Modifier.fillMaxSize().background(Color.Black),) {


        Image(
            painter = painterResource(id = R.drawable.background_image),
            contentDescription = "",
            modifier = Modifier.fillMaxSize().alpha(0.4f),
            contentScale = ContentScale.Crop
        )


        LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
            item {
                //  header
                AddTasksHeaderView("Add Task") {
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
                    }
                )

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
                AddTagsListView(allTags, navController) {
                    viewModel.selectedTags.value = it
                    viewModel.selectedTags.value
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTasksHeaderView(title: String, onBackClicked: () -> Boolean) {
    val searchPhrase = remember { mutableStateOf("") }

    Column {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Card(
                modifier = Modifier
                    .weight(0.18f)
                    .padding(7.dp),
                shape = RoundedCornerShape(20),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 16.dp
                ),
                onClick = {
                    onBackClicked.invoke()
                }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.custom_arrow_icon),
                    contentDescription = "profile picture",
                    modifier = Modifier
                        .size(40.dp)
                        .align(Alignment.CenterHorizontally),
                    contentScale = ContentScale.Crop,
                )
            }
            Text(
                title,
                modifier = Modifier
                    .weight(0.8f)
                    .fillMaxWidth()
                    .padding(end = 60.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Navy,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(20.dp))


    }

}
