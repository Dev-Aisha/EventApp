package com.example.eventapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class Task(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "task_id")
    var taskId: Long?= null,
    @ColumnInfo(name = "task_title")
    val title: String,
    @ColumnInfo(name = "task_description")
    var description: String,
    @ColumnInfo(name = "date")
    var date: String,
    @ColumnInfo(name = "time_from")
    var timeFrom: String? ="",
    @ColumnInfo(name = "time_to")
    var timeTo: String? = "",
    @ColumnInfo(name = "task_type")
    var taskType: String,
    @ColumnInfo(name = "task_tag_name")
    var tagName: String ="",


)

enum class TaskType(val type: String) {
    Pending("Pending"),
    OnGoing("On Going"),
    Cancelled("Cancelled"),
    Completed("Completed")
}



