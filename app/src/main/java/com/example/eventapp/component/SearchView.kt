package com.example.eventapp.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.eventapp.screens.task.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView(search : (parameter: String)-> Unit){
    val searchPhrase = remember { mutableStateOf("") }
    val viewModel: TaskViewModel = hiltViewModel()

    TextField(
        value = searchPhrase.value,

        modifier = Modifier.fillMaxWidth(),

        shape = RoundedCornerShape(20.dp),
        keyboardActions = KeyboardActions(
            onSearch = {

                val tagsWithTasksList = viewModel.tagWithTasks



                val items = if (searchPhrase.value == "") {
                    tagsWithTasksList
            }else{


                }

            }
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search
        ),

        onValueChange =
        {
            searchPhrase.value = it
            search(searchPhrase.value)
        },

        placeholder =
        {
            Text(text = "Search for task")
        },

        leadingIcon =
        {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon")
        },

        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.LightGray,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.LightGray,
            cursorColor = Color.Black,
    ),
    )

}




//@Composable
//fun SearchTask(search: MutableState<String>) {
//    val viewModel: TaskViewModel = hiltViewModel()
//
//    val tagsWithTasksList = viewModel.tagWithTasks
//
//
//
//    val items = if (search.value == "") {
//        tagsWithTasksList
//
//    } else {
//
//    }
//}