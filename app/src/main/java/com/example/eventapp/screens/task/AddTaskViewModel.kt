package com.example.eventapp.screens.task

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventapp.data.entity.TagWithTaskLists
import com.example.eventapp.data.entity.Tags
import com.example.eventapp.data.entity.Task
import com.example.eventapp.data.entity.TaskTagCrossRef
import com.example.eventapp.data.entity.TaskType
import com.example.eventapp.data.entity.TaskWithTags
import com.example.eventapp.data.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(val taskRepository: TaskRepository) : ViewModel() {



    val title: MutableState<String> = mutableStateOf("")
    val description: MutableState<String> = mutableStateOf("")
    val taskDate: MutableState<String> = mutableStateOf("")
    val startTime: MutableState<String> = mutableStateOf("")
    val endTime: MutableState<String> = mutableStateOf("")
    val taskType: MutableState<String> = mutableStateOf(TaskType.OnGoing.name)
    val category: MutableState<String> = mutableStateOf("")

//    val cancelledTasks = taskRepository.getTagsWithTask(TaskType.Cancelled.name)
//    val pendingTasks = taskRepository.getTagsWithTask(TaskType.Pending.name)
//    val completedTasks = taskRepository.getTagsWithTask(TaskType.Completed.name)
//    val onGoingTasks = taskRepository.getTagsWithTask(TaskType.OnGoing.name)

    //tag
    val tagName: MutableState<String> = mutableStateOf("")
    val tagColor: MutableState<String> = mutableStateOf("")
    val tagIcon: MutableState<String> = mutableStateOf("")
    val isSelected: MutableState<Boolean> = mutableStateOf(false)

    val selectedTags = mutableStateOf<List<Tags>>(emptyList())

    var allTags: MutableStateFlow<List<Tags>> = MutableStateFlow(emptyList())



    fun addTask() {

        viewModelScope.launch {
            val task = Task(
                title = title.value,
                description = description.value,
                date = taskDate.value,
                timeFrom = startTime.value,
                timeTo = endTime.value,
                taskType = taskType.value,
                tagName = category.value
            )
            insertTaskWithTags(
                task,
                selectedTags.value.toList()
            )
        }
    }
    fun addTag() {
        viewModelScope.launch {
            taskRepository.insertTag(
            Tags(
                tagName.value,
                tagColor.value,
                tagIcon.value,
                isSelected = true
            )
            )
    }
        }
    private suspend fun insertTaskWithTags(task: Task, tags: List<Tags>) {
        val taskId = taskRepository.insertTask(task)
        val taskTagCrossRefs = tags.map { TaskTagCrossRef(taskId, it.name)}
                taskRepository.insertTaskTagsCrossRef(taskTagCrossRefs)
        }





    fun updateTask(taskId: Long?) {
        viewModelScope.launch {

            val task = Task(
                taskId = taskId,
                title = title.value,
                description = description.value,
                date = taskDate.value,
                timeFrom = startTime.value,
                timeTo = endTime.value,
                taskType = taskType.value,
                tagName = tagName.value
            )

            taskRepository.updateTaskWithTags(task, selectedTags.value)

        }

    }


    fun getSelectedTask(taskId: Long) {
        viewModelScope.launch {
            val selectedTask = taskRepository.getTaskWithTagsById(taskId)

            title.value = selectedTask.task.title
            description.value = selectedTask.task.description
            taskDate.value = selectedTask.task.date
            startTime.value = selectedTask.task.timeFrom.orEmpty()
            endTime.value = selectedTask.task.timeTo.orEmpty()
            selectedTags.value = selectedTask.tags //all tags is selected false
        }
    }

    fun getAllTag() {
        viewModelScope.launch {
            taskRepository.getAllTags().collect {
                allTags.value = it
            }
        }
    }


    }



//***********************************************
