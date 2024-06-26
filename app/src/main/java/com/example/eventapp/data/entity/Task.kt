package com.example.eventapp.data.entity

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Done
import androidx.compose.ui.graphics.toArgb
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.eventapp.R
import com.example.eventapp.ui.theme.LightPurple
import com.example.eventapp.getIconName
import com.example.eventapp.ui.theme.LightBlue
import com.example.eventapp.ui.theme.LightGreen
import com.example.eventapp.ui.theme.LightRed

@Entity(tableName = "task_table")
data class Task(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "task_Id")
    var taskId: Long? = null,
    @ColumnInfo(name = "task_title")
    val title: String,
    @ColumnInfo(name = "task_description")
    val description: String,
    @ColumnInfo(name = "date")
    val date: String,
    @ColumnInfo(name = "time_from")
    val timeFrom: String? = "",
    @ColumnInfo(name = "time_to")
    val timeTo: String? = "",
    @ColumnInfo(name = "task_type")
    val taskType: String,
    @ColumnInfo(name = "task_tag_name")
    val tagName: String = ""
)

enum class TaskType(val resourceId: Int, val color: String, val icon: String) {

    Pending(R.string.task_pending, LightPurple.toArgb().toString(), getIconName(Icons.Outlined.DateRange)),
    OnGoing(R.string.task_ongoing, LightGreen.toArgb().toString(), getIconName(Icons.Outlined.Build)),
    Cancelled(R.string.task_cancelled, LightRed.toArgb().toString(), getIconName(Icons.Outlined.Delete)),
    Completed(R.string.task_completed, LightBlue.toArgb().toString(), getIconName(Icons.Outlined.Done))

}

data class SearchResults(
    val taskResults: List<TaskWithTags>,
    val tagResults: List<TagWithTaskLists>
)