package com.example.eventapp.data.repository

import com.example.eventapp.data.dao.TaskDao
import com.example.eventapp.data.entity.SearchResults
import com.example.eventapp.data.entity.TagWithTaskLists
import com.example.eventapp.data.entity.Tags
import com.example.eventapp.data.entity.Task
import com.example.eventapp.data.entity.TaskTagCrossRef
import com.example.eventapp.data.entity.TaskWithTags
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val taskDao: TaskDao
) {

    suspend fun insertTask(task: Task): Long {
        return taskDao.addTask(task)
    }

    suspend fun insertTaskTagsCrossRef(taskTagsCrossRef: List<TaskTagCrossRef>) {
        taskDao.insertTaskTagCrossRefs(taskTagsCrossRef)

    }

    suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(task)
    }

    fun getAllTasks(): Flow<List<Task>> {
        return taskDao.getAllTasks()
    }

    suspend fun insertTag(tag: Tags) {
        taskDao.upsertTag(tag)
    }


    suspend fun deleteTag(tag: Tags) {
        taskDao.deleteTag(tag)
    }

//    fun getTagsWithTask(tagName: String): Flow<List<TagWithTaskLists>> {
//        return taskDao.getTagsWithTask(tagName)
//    }

    fun getAllTags(): Flow<List<Tags>> {
        return taskDao.getAllTags()
    }

    suspend fun insertTagList(tagList: List<Tags>) {
        return taskDao.upsertTagList(tagList)
    }


    fun sortTasksByDate(date: String): Flow<List<TaskWithTags>> {
        return taskDao.sortByCreationDate(date)
    }

    fun getTagsWithTasks(): Flow<List<TaskWithTags>> {
        return taskDao.getTaskWithTags()
    }

    fun getTagWithTaskLists() = taskDao.getTagWithTaskLists()


//    fun getTaskById(taskId: Long): Flow<Task> {
//        return taskDao.getTaskById(taskId)
//    }


    suspend fun updateTaskWithTags(task: Task, tags: List<Tags>) {
        taskDao.updateTaskWithTags(task, tags)
    }

    fun getAllTaskWithTags() = taskDao.getAllTaskWithTags()


    suspend fun getTaskWithTagsById(taskId: Long) = taskDao.getTaskWithTagsById(taskId)




    //88888888888888888888

    suspend fun insertTaskTagCrossRefs(taskTagCrossRefs: List<TaskTagCrossRef>) {
        taskDao.insertTaskTagCrossRefs(taskTagCrossRefs)
    }



    fun getTagWithTasksList(tagName: String): Flow<TagWithTaskLists> {
        return taskDao.getTagsWithTask(tagName)
    }



    suspend fun searchCombined(searchQuery: String): SearchResults {
        return taskDao.searchCombined(searchQuery)
    }


    fun getTaskCountByDateAndType(date: String, taskType: String): Flow<Int> {
        return taskDao.getTaskCountByDateAndType(date, taskType)
    }


}