package com.example.eventapp.screens.task

import android.net.Uri
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.eventapp.R
import com.example.eventapp.component.TaskCard
import com.example.eventapp.component.TaskCategoryCard
import com.example.eventapp.data.entity.TaskType
import com.example.eventapp.navigation.Screens
import com.example.eventapp.ui.theme.White
import com.google.firebase.auth.FirebaseUser
import java.time.LocalDate


//@Composable
//fun HomeScreen(invoke: FirebaseUser?, navController: NavHostController, viewModel: TaskViewModel) {
//    LaunchedEffect(Unit) {
//        viewModel.sortTasksByDate(LocalDate.now().toString())
//    }
//
//    val completedTask by viewModel.completedTasks.collectAsState(initial = null)
//    val cancelledTask by viewModel.cancelledTasks.collectAsState(initial = null)
//    val onGoingTask by viewModel.onGoingTasks.collectAsState(initial = null)
//    val pendingTask by viewModel.pendingTasks.collectAsState(initial = null)
//
//    val tasksList = viewModel.taskWithTags
//
//    Box(modifier = Modifier
//        .fillMaxSize()
//        .background(Color.Black),) {
//        Image(
//            painter = painterResource(id = R.drawable.back_main),
//            contentDescription = "",
//            modifier = Modifier
//                .fillMaxSize()
//                .alpha(0.4f),
//            contentScale = ContentScale.Crop
//        )
//
//
//        LazyColumn(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(horizontal = 16.dp)
//                .padding(bottom = 100.dp)
//                .semantics {
//                    contentDescription = "Home Screen"
//                }, verticalArrangement = Arrangement.spacedBy(6.dp)
//        ) {
//            item {
//                HeaderView(invoke?.displayName.orEmpty(), invoke?.photoUrl)
//                Spacer(modifier = Modifier.size(16.dp))
//                Text(
//                    stringResource(id = R.string.my_tasks),
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 24.sp,
//                    color = White
//                )
//            }
//            //task Type View
//            item {
//                Row(modifier = Modifier.padding(vertical = 4.dp)) {
//                    Column(
//                        modifier = Modifier
//                            .weight(0.4f)
//                            .padding(vertical = 12.dp)
//                    ) {
//                        TaskCategoryCard(
//                            TaskType.Completed,
//                            completedTask.value?.tasks?.size.toString().plus(" Task"),
//                            Color(0xFF4F7A8C),
//                            height = 230.dp,
//                            onClick = {
//                                navController.navigate("${Screens.MainApp.TaskByCategory.route}/${TaskType.Completed.name}")
//
//                            },
//                            image = {
//                                Image(
//                                    painter = painterResource(id = R.drawable.n_comp),
//                                    contentDescription = "",
//                                    modifier = Modifier.size(85.dp)
//                                )
//
//                            }
//                        )
//                        TaskCategoryCard(
//                            TaskType.Cancelled,
//                            cancelledTask?.first()?.tasks?.size.toString(),
//                            Color(0xFF6D4A4A),
//                            height = 200.dp,
//                            onClick = {
//                                navController.navigate("${Screens.MainApp.TaskByCategory.route}/${TaskType.Cancelled.name}")
//                            },
//                            image = {
//                                Image(
//                                    painter = painterResource(id = R.drawable.close_square),
//                                    contentDescription = "",
//                                    modifier = Modifier.size(40.dp)
//                                )
//
////                                Icon(
////                                    imageVector = Icons.TwoTone.CheckCircle,
////                                    contentDescription = "",
////                                    tint = Color.White,
////                                    modifier = Modifier.size(40.dp)
////
////                                )
//                            }
//                        )
//                    }
//                    /////
//                    Column(
//                        modifier = Modifier
//                            .weight(0.4f)
//                            .padding(vertical = 12.dp)
//                    ) {
//                        TaskCategoryCard(
//                            TaskType.Pending,
//                            pendingTask?.first()?.tasks?.size.toString(),
//                            Color(0xFF5A5F89),
//                            height = 200.dp,
//                            onClick = {
//                                navController.navigate("${Screens.MainApp.TaskByCategory.route}/${R.string.task_pending}")
//                            },
//                            image = {
//                                Image(
//                                    painter = painterResource(id = R.drawable.time_square),
//                                    contentDescription = "",
//                                    modifier = Modifier.size(40.dp)
//                                )
//                            })
//                        TaskCategoryCard(
//                            TaskType.OnGoing,
//                            onGoingTask?.first()?.tasks?.size.toString(),
//                            Color(0xFF517C5D),
//                            height = 230.dp,
//                            onClick = {
//                                navController.navigate("${Screens.MainApp.TaskByCategory.route}/${R.string.task_ongoing}")
//
//                            },
//                            image = {
//                                Image(
//                                    painter = painterResource(id = R.drawable.n_going),
//                                    contentDescription = "",
//                                    modifier = Modifier.size(90.dp)
//                                )
//                            })
//                    }
//
//                }
//            }
//            //today task view
//            item {
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(vertical = 24.dp),
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.Absolute.SpaceBetween
//
//                ) {
//
//                    Text(
//                        stringResource(id = R.string.today_tasks),
//                        fontWeight = FontWeight.Bold,
//                        fontSize = 24.sp,
//                        color = White
//                    )
//                    Text(
//                        stringResource(id = R.string.view_all),
//                        modifier = Modifier
//                            .padding(top = 8.dp)
//                            .clickable {
//                                navController.navigate(Screens.MainApp.TaskByDate.route)
//                            },
//                        fontSize = 12.sp,
//                        color = Color(0xFFFFFFFF)
//                    )
//                }
//            }
//            items(tasksList.value) {
//                TaskCard(
//                    taskTitle = it.task.title,
//                    timeFrom = it.task.timeFrom,
//                    timeTo = it.task.timeTo,
//                    tag = it.tags,
//                    task= it.task,
//                    viewModel = viewModel,
//                    navController = navController
//                )
//            }
//
//        }
//    }
//}
//
//
//

@Composable
fun HomeScreen(invoke: FirebaseUser?, navController: NavHostController, viewModel: TaskViewModel) {


    LaunchedEffect(Unit) {
        viewModel.sortTasksByDate(LocalDate.now().toString())
    }

    val completedTask = viewModel.completedTasks.collectAsState(initial = null)
    val cancelledTask = viewModel.cancelledTasks.collectAsState(initial = null)
    val onGoingTask = viewModel.onGoingTasks.collectAsState(initial = null)
    val pendingTask = viewModel.pendingTasks.collectAsState(initial = null)

    val tasksList = viewModel.taskWithTags.value


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
    ) {
        Image(
            painter = painterResource(id = R.drawable.back_main),
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.4f),
            contentScale = ContentScale.Crop
        )


        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(bottom = 100.dp)
                .semantics {
                    contentDescription = "Home Screen"
                }, verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            item {
                HeaderView(invoke?.displayName.orEmpty(), invoke?.photoUrl)
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    stringResource(id = R.string.my_tasks),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = White
                )
            }
            //task Type View
            item {
                Row(modifier = Modifier.padding(vertical = 4.dp)) {
                    Column(
                        modifier = Modifier
                            .weight(0.4f)
                            .padding(vertical = 12.dp)
                    ) {
                        TaskCategoryCard(
                            TaskType.Completed,
                            completedTask.value?.tasks?.size.toString(),
                            Color(0xFF4F7A8C),
                            height = 220.dp,
                            onClick = {
                                navController.navigate("${Screens.MainApp.TaskByCategory.route}/${TaskType.Completed}")

                            },
                            image = {
                                Image(
                                    painter = painterResource(id = R.drawable.n_comp),
                                    contentDescription = "",
                                    modifier = Modifier.size(85.dp)
                                )

                            }
                        )

                        TaskCategoryCard(
                            TaskType.Cancelled,
                            pendingTask.value?.tasks?.size.toString(),
                            Color(0xFF6D4A4A),
                            height = 190.dp,
                            onClick = {
                                navController.navigate("${Screens.MainApp.TaskByCategory.route}/${TaskType.Cancelled}")
                            },
                            image = {
                                Image(
                                    painter = painterResource(id = R.drawable.close_square),
                                    contentDescription = "",
                                    modifier = Modifier.size(40.dp)
                                )
                            }


                        )
                    }
                    /////
                    Column(
                        modifier = Modifier
                            .weight(0.4f)
                            .padding(vertical = 12.dp)
                    ) {
                        TaskCategoryCard(
                            TaskType.Pending,
                            cancelledTask.value?.tasks?.size.toString(),
                            Color(0xFF5A5F89),
                            height = 190.dp,
                            onClick = {
                                navController.navigate("${Screens.MainApp.TaskByCategory.route}/${TaskType.Pending}")
                            },

                            image = {
                                Image(
                                    painter = painterResource(id = R.drawable.time_square),
                                    contentDescription = "",
                                    modifier = Modifier.size(40.dp)
                                )
                            })

                        TaskCategoryCard(
                            TaskType.OnGoing,
                            onGoingTask.value?.tasks?.size.toString(),
                            Color(0xFF517C5D),
                            height = 220.dp,
                            onClick = {
                                navController.navigate("${Screens.MainApp.TaskByCategory.route}/${TaskType.OnGoing}")

                            },

                            image = {
                              Image(
                                  painter = painterResource(id = R.drawable.n_going),
                                  contentDescription = "",
                                  modifier = Modifier.size(90.dp)
                                )
                            }
                        )


                    }

                }
            }
            //today task view
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Absolute.SpaceBetween

                ) {

                    Text(
                        "Today Tasks",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = Color.White
                    )
                    Text(
                        "View all",
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .clickable {
                                navController.navigate(Screens.MainApp.TaskByDate.route)
                            },
                        fontSize = 12.sp,
                        color = Color.White
                    )
                }
            }
            items(tasksList.orEmpty()) {
                TaskCard(
                    taskTitle = it.task.title,
                    timeFrom = it.task.timeFrom,
                    timeTo = it.task.timeTo,
                    tag = it.tags,
                    task = it.task,
                    viewModel = viewModel,
                    navController = navController,

                    )
            }
        }
    }
}



@Composable
fun HeaderView(userName: String, photo: Uri?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Absolute.SpaceBetween

    ) {

        Column {
            Text(
                text = stringResource(id = R.string.hi, userName),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = White
            )
            Text(
                text= stringResource(id = R.string.let),
                modifier = Modifier.padding(vertical = 8.dp),
                fontSize = 14.sp,
                color = Color.LightGray
            )
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
            //todo use coil
            if (photo.toString().isEmpty()) {
                Image(
                    painter = painterResource(id = R.drawable.user_avatar_male),
                    contentDescription = "profile picture",
                    modifier = Modifier.size(64.dp),
                    contentScale = ContentScale.Crop
                )
            } else {
                AsyncImage(
                    model = photo,
                    contentDescription = "profile picture",
                    modifier = Modifier.size(64.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}
