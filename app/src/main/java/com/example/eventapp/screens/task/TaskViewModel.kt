package com.example.eventapp.screens.task

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventapp.data.entity.TagWithTaskLists
import com.example.eventapp.data.entity.Tags
import com.example.eventapp.data.entity.Task
import com.example.eventapp.data.entity.TaskType
import com.example.eventapp.data.entity.TaskWithTags
import com.example.eventapp.data.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val taskRepository: TaskRepository
) : ViewModel() {


    val tasks = mutableStateOf<List<Task>>(emptyList())

    val tags = taskRepository.getAllTags()
    val cancelledTasks = taskRepository.getTagWithTasksList(TaskType.Cancelled.name)
    val pendingTasks = taskRepository.getTagWithTasksList(TaskType.Pending.name)
    val completedTasks = taskRepository.getTagWithTasksList(TaskType.Completed.name)
    val onGoingTasks = taskRepository.getTagWithTasksList(TaskType.OnGoing.name)

    val tagWithTasks = mutableStateOf<List<TagWithTaskLists>>(emptyList())
    val taskWithTags =  mutableStateOf<List<TaskWithTags>> (emptyList())

//    val tagWithTasks = MutableStateFlow<List<TagWithTaskLists>>(emptyList())
//    val taskWithTags : MutableState<List<TaskWithTags>> = mutableStateOf(emptyList())

    init {
        //add base tags
        viewModelScope.launch {
            val tagsList = TaskType.values().map {
                Tags(it.name, it.color, it.icon, true)
            }
            taskRepository.insertTagList(tagsList)
        }
        getTagWithTaskLists()
    }

    fun sortTasksByDate(date: String) {
        viewModelScope.launch {
            taskRepository.sortTasksByDate(date).collect {
                taskWithTags.value = it
            }
        }
    }

    fun searchInTasksAndTags(query: String) {
        viewModelScope.launch {
            taskWithTags.value = taskRepository.searchCombined(query).taskResults
            tagWithTasks.value = taskRepository.searchCombined(query).tagResults
        }
    }

    fun getTagWithTaskLists() {
        viewModelScope.launch {
            taskRepository.getTagWithTaskLists().collect {
                tagWithTasks.value = it
            }
        }

    }

    fun deleteTask(task: Task){
        viewModelScope.launch {
            taskRepository.deleteTask(task)
        }
    }





   // val completedTasksCount = taskRepository.getTaskCountByDateAndType(currentDate,TaskType.Completed.name)
    fun getTaskCountByDateAndType(date: String, taskType: String) = taskRepository.getTaskCountByDateAndType(date, taskType)







}