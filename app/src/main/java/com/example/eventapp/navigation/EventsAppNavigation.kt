package com.example.eventapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.eventapp.component.MonthlyHorizontalCalendarView
import com.example.eventapp.component.TimePickerr
import com.example.eventapp.screens.auth.AuthViewModel
import com.example.eventapp.screens.auth.LoginScreen
import com.example.eventapp.screens.auth.SignUpScreen
import com.example.eventapp.screens.auth.SplashScreen
import com.example.eventapp.screens.chart.ChartScreen
import com.example.eventapp.screens.task.AddTagDialog
import com.example.eventapp.screens.task.AddTaskScreen
import com.example.eventapp.screens.task.AddTaskViewModel
import com.example.eventapp.screens.task.CategoryScreen
import com.example.eventapp.screens.task.EditTaskScreen
import com.example.eventapp.screens.task.HomeScreen
import com.example.eventapp.screens.task.SettingScreen
import com.example.eventapp.screens.task.TaskByDateScreen
import com.example.eventapp.screens.task.TaskViewModel
import com.example.eventapp.screens.task.TasksByCategory
import com.google.firebase.auth.FirebaseUser
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer


@Composable
fun EventsAppNavigation(
    authViewModel: AuthViewModel,
    navController: NavHostController
) {
    val context = LocalContext.current
    NavHost(
        navController = navController,
        startDestination = authViewModel.isSignedIn.value,
    ) {
        authNavigation(navController, authViewModel)
        mainAppNavigation(navController, logout = { authViewModel.logout(context) }) {
            authViewModel.auth.currentUser
        }
    }
}


@OptIn(ExperimentalComposeUiApi::class)
fun NavGraphBuilder.authNavigation(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    navigation(
        startDestination = Screens.Authentication.Splash.route,
        route = Screens.Authentication.route,
    ) {
        composable(Screens.Authentication.Splash.route) {
            SplashScreen(navController)
        }
        composable(Screens.Authentication.SignUp.route) {
            SignUpScreen(navController, authViewModel)
        }

        composable(Screens.Authentication.Login.route) {
            LoginScreen(navController, authViewModel)
        }
    }
}


fun NavGraphBuilder.mainAppNavigation(
    navController: NavHostController,
    logout: () -> Unit,
    userName: () -> FirebaseUser?
) {
    navigation(
        startDestination = Screens.MainApp.Home.route,
        route = Screens.MainApp.route,
    ) {
        composable(Screens.MainApp.Home.route) {
            val viewModel: TaskViewModel = hiltViewModel()
            HomeScreen(userName.invoke(), navController, viewModel)
        }






        composable(Screens.MainApp.TaskByDate.route) {
            val viewmodel: TaskViewModel = hiltViewModel()
            TaskByDateScreen(viewmodel, navController)
        }






        composable(Screens.MainApp.AddScreen.route) {
            val viewmodel: AddTaskViewModel = hiltViewModel()
            viewmodel.taskDate.value = it.savedStateHandle.get<String>("selectedDate").orEmpty()

            AddTaskScreen(navController, viewmodel)
        }




        composable(Screens.MainApp.CategoryScreen.route) {
            val taskViewModel: TaskViewModel = hiltViewModel()
            CategoryScreen(userName.invoke(), taskViewModel, navController, logout)



//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(Color.Red)
//            ) {
//
//
//                Button(onClick = {
//                    logout.invoke()
//                }) {
//                    Text(text = "SignOut")
//                }
//            }

        }




        composable(Screens.MainApp.StaticsScreen.route) {
            ChartScreen(modifier = Modifier)

        }





        composable(Screens.MainApp.Setting.route){
            SettingScreen(navController)
        }




        dialog(
            Screens.MainApp.DateDialog.route, dialogProperties = DialogProperties(
                dismissOnClickOutside = true,
                dismissOnBackPress = true
            )
        ) {

            MonthlyHorizontalCalendarView(navController) {
                navController.popBackStack()
            }
        }
        dialog(Screens.MainApp.TimeDialog.route){
            TimePickerr(showDatePicker =true)
        }

        dialog(
            Screens.MainApp.AddTagDialog.route, dialogProperties = DialogProperties(
                dismissOnClickOutside = true,
                dismissOnBackPress = true
            )
        ) {
            val addTaskViewModel: AddTaskViewModel = hiltViewModel()
            AddTagDialog(navController, addTaskViewModel)
        }

        composable("${Screens.MainApp.TaskByCategory.route}/{tagName}", arguments = listOf(
            navArgument("tagName") {
                type = NavType.StringType
            }
        )
        )
        {
            navArgument ->
            val taskViewModel: TaskViewModel = hiltViewModel()
            val tagWithTaskLists = taskViewModel.tagWithTasks.value.firstOrNull {
                it.tag.name == navArgument.arguments?.getString(
                    "tagName"
                ).orEmpty()
            }
            val viewModel: TaskViewModel = hiltViewModel()
            TasksByCategory(tagWithTaskLists, navController, viewModel = viewModel)
        }
//
//        composable(
//            "${Screens.MainApp.EditTaskScreen.route}/{title}/{date}/{description}",
//            arguments = listOf(
//                navArgument("title") {
//                    type = NavType.StringType
//                },
//                navArgument("date") {
//                    type = NavType.StringType
//                },
//                navArgument("description") {
//                    type = NavType.StringType
//                }
//            )
//        ) {
//            val viewModel: AddTaskViewModel = hiltViewModel()
//            val title = it.arguments?.getString("title").orEmpty()
//            val date = it.arguments?.getString("date").orEmpty()
//            val description = it.arguments?.getString("description").orEmpty()
//
//            EditTaskScreen(navController, viewModel.taskType, viewModel.taskDate, viewModel.startTime,viewModel.description, )
//        }

//
//        composable(Screens.MainApp.EditTaskScreen.route) {
//            val viewModel: AddTaskViewModel = hiltViewModel()
//            EditTaskScreen(navController, viewModel)
//        }

        composable(
            "${Screens.MainApp.EditTaskScreen.route}/{taskId}", arguments =
            listOf(navArgument("taskId") {
                type = NavType.LongType
            }
            )
        )
        {
            val viewmodel: AddTaskViewModel = hiltViewModel()
            EditTaskScreen(navController, viewmodel, it.arguments?.getLong("taskId"), it)
        }



//        composable(Screens.MainApp.CategoryScreen.route) {
////            val taskViewModel: TaskViewModel = hiltViewModel()
////            CategoryScreen(userName.invoke(), taskViewModel, navController, logout)
//        }




//        dialog(Screens.MainApp.TimeFromDialog.route){
//            DatePicker(showDatePicker =true)
//        }
//        dialog(Screens.MainApp.TimeToDialog.route){
//            DatePicker(showDatePicker =true)
//        }

//        composable(Screens.MainApp.TaskByDate.route) {
//            val viewModel: TaskViewModel = hiltViewModel()
//
//            LazyColumn(
//                modifier = Modifier
//                    .fillMaxSize()
//            ){
//                item{
//                    val result = viewModel.tasks.collectAsState(null)
//                    val tags = viewModel.tags.collectAsState(null)
//                    val taskByTag = viewModel.tasksbyTags.collectAsState(null)
//
//                    Text(text = result.value.toString())
//                    Spacer(modifier = Modifier.padding(vertical = 12.dp))
//                    Text(text = taskByTag.value.toString())
//                    Spacer(modifier = Modifier.padding(vertical = 12.dp))
//                    Text(text = tags.value.toString())
//
//                }
//            }
//        }

    }
}

fun NavOptionsBuilder.popUpToTop(navController: NavController) {
    popUpTo(navController.currentBackStackEntry?.destination?.route ?: return) {
        saveState = true
        inclusive = true
    }
}
