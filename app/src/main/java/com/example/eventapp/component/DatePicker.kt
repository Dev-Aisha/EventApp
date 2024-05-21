package com.example.eventapp.component

import android.icu.util.LocaleData
import android.widget.Toast
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.eventapp.ui.theme.Pink40
import com.example.eventapp.ui.theme.PrimaryColor
import com.example.eventapp.ui.theme.Purple80
import com.example.eventapp.ui.theme.PurpleGrey80
import com.google.android.libraries.places.api.model.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePicker(showDatePicker: Boolean){

    var selectedDate by remember { mutableStateOf("") }

    val datePickerState = rememberDatePickerState(
        yearRange = 1990..2030
    )
    var showDatePicker by remember { mutableStateOf(showDatePicker) }

    val timePickerState = rememberTimePickerState(
        initialHour = 12,
        initialMinute = 30,
    )

    val context = LocalContext.current



    // date picker component
    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = {
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        val selectedDateMillis = datePickerState.selectedDateMillis


                        var selectedDate = Calendar.getInstance().apply {
                            timeInMillis = datePickerState.selectedDateMillis!!
                        }
                        if (selectedDate.after(Calendar.getInstance())) {
                            val dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy")
//                            selectedDate =
//                                LocalDate.(selectedDate.get(Calendar.YEAR),
//                                    selectedDate.get(Calendar.MONTH) + 1,
//                                    selectedDate.get(Calendar.DAY_OF_MONTH)).format(dateFormat)
                            showDatePicker = false

                        } else {
                            Toast.makeText(
                                context,
                                "Selected date should be after today, please select again",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDatePicker = false
                    }
                ) { Text("Cancel") }
            },
            colors = DatePickerDefaults.colors(
                containerColor = PurpleGrey80,
            )
        )
        {

            androidx.compose.material3.DatePicker(
                state = datePickerState,
                colors = DatePickerDefaults.colors(
                    todayContentColor = Pink40,
                    todayDateBorderColor = Color.Yellow,
                    selectedDayContentColor = Purple80,
                    dayContentColor = Color.DarkGray,
                    selectedDayContainerColor = PrimaryColor,
                )
            )
        }
    }


}