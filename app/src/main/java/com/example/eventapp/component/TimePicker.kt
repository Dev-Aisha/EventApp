package com.example.eventapp.component

import android.app.TimePickerDialog
import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eventapp.R
import com.example.eventapp.ui.theme.LightBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePicker(
    label: String,
    context: Context, initHour: Int, initMinute: Int, value: MutableState<String>
) {
    val timePickerDialog = TimePickerDialog(
        context,
        R.style.Theme_EventApp,
        { _, hour: Int, minute: Int ->
            val AM_PM: String
            AM_PM = if (hour < 12) {
                "AM"
            } else {
                "PM"
            }

            value.value = "$hour:$minute $AM_PM"
        }, initHour, initMinute, false
    )
    Column() {

        Text(
            label,
            Modifier.padding(top = 20.dp, end = 20.dp, start = 20.dp),
            fontWeight = FontWeight.Bold,
            style = TextStyle(color = LightGray, fontSize = 16.sp)
        )

        TextField(
            modifier = Modifier
                .padding(horizontal = 6.dp)
                .wrapContentWidth(),
            colors = TextFieldDefaults.textFieldColors(

                cursorColor = LightBlue,
                focusedIndicatorColor = LightBlue,
                focusedLabelColor = LightBlue
            ),
            readOnly = true,
            value = value.value,
            onValueChange = {
                value.value = it
            },
            trailingIcon = {
                Icon(
                    Icons.Outlined.Notifications, "time",
                    modifier = Modifier.clickable {
                        timePickerDialog.show()
                    },
                )
            }
        )
    }
}