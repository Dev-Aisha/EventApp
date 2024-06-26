package com.example.eventapp.screens.task
//
//import androidx.compose.runtime.MutableState
//import androidx.compose.runtime.mutableStateOf
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.eventapp.data.entity.Tags
//import com.example.eventapp.data.entity.Task
//import com.example.eventapp.data.entity.TaskType
//import com.example.eventapp.data.repository.TaskRepository
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.launch
//import javax.inject.Inject
//
//@HiltViewModel
//class AddTaskViewwModel @Inject constructor(val taskRepository: TaskRepository) : ViewModel() {
//
//    val title: MutableState<String> = mutableStateOf("")
//    val description: MutableState<String> = mutableStateOf("")
//    val taskDate: MutableState<String> = mutableStateOf("")
//    val startTime: MutableState<String> = mutableStateOf("")
//    val endTime: MutableState<String> = mutableStateOf("")
//    val taskType: MutableState<String> = mutableStateOf(TaskType.OnGoing.type)
//    val category: MutableState<String> = mutableStateOf("")
//
//    var allTasks = taskRepository.getAllTasks()
//
//
//    fun addTask() {
//        viewModelScope.launch {
//            val task = Task(
//                title = title.value,
//                description = description.value,
//                date = taskDate.value,
//                timeFrom = startTime.value,
//                timeTo = endTime.value,
//                taskType = taskType.value,
//                tagName = category.value
//            )
//            taskRepository.insertTask(task)
//        }
//    }
//
//    fun addTags(list: List<Tags>) {
//        viewModelScope.launch {
//            taskRepository.insertTagList(list)
//        }
//    }
//}