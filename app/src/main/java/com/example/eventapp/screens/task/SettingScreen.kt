package com.example.eventapp.screens.task


import android.app.Activity
import android.app.LocaleManager
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton
import androidx.compose.material3.Text
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.LocaleListCompat
import java.util.Locale
import android.os.LocaleList

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

import androidx.navigation.NavHostController
import com.example.eventapp.R
import com.example.eventapp.ui.theme.Navy
import com.google.firebase.auth.FirebaseAuth


@Composable
fun SettingScreen(navController: NavHostController) {
    lateinit var auth: FirebaseAuth
    auth = FirebaseAuth.getInstance()

    var showDeleteDialog by remember { mutableStateOf(false) }
    var showLanguageDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val languages = listOf("English", "Arabic", "French", "Chinese")
    var selectedLanguage by remember { mutableStateOf(languages[0]) }

    // Fetching current app language to show to the user
    LaunchedEffect(Unit) {
        selectedLanguage = getCurrentAppLanguage(context)
    }


    //Delete Account Dialog

    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text(text = "Delete Account") },
            text = { Text("Are you sure you want to delete this account?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        deleteUserAccount(auth) {
                                                success, message ->
                            if (success) {
                                Toast.makeText(context, "Account deleted successfully.", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(context, "Failed to delete account: $message", Toast.LENGTH_SHORT).show()
                            }
                        }
                        showDeleteDialog = false
                    }
                ) {
                    Text("Sure")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showDeleteDialog = false }
                ) {
                    Text("Cancel")
                }
            }
        )
    }



    //change Language Dialog

    if (showLanguageDialog) {
        AlertDialog(
            onDismissRequest = { showLanguageDialog = false },
            title = { Text(text = "Select Language") },
            text = {
                Column {
                    languages.forEach { language ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        ) {
                            RadioButton(
                                selected = (language == selectedLanguage),
                                onClick = { selectedLanguage = language }
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = language)
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showLanguageDialog = false
                        changeAppLanguage(context, selectedLanguage)
                        Toast.makeText(context, "Language changed to $selectedLanguage", Toast.LENGTH_SHORT).show()
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showLanguageDialog = false }
                ) {
                    Text("Cancel")
                }
            }
        )
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
    ) {
        Image(
            painter = painterResource(id = R.drawable.background_image),
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.4f),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.padding(top = 30.dp))

        Column(modifier = Modifier.padding(16.dp)) {



            TasksHeaderSetting(stringResource(id = R.string.setting)) {
                navController.popBackStack()


            }

            Text(
                text = stringResource(id = R.string.general),
                fontSize = 25.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))


            // Box for Language setting
            Box(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(Color.White)
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(5.dp)
                ) {
                    TextButton(
                        onClick = { showLanguageDialog = true }
                    ) {
                        Text(
                            text = stringResource(id = R.string.language),
                            fontSize = 18.sp
                        )
                    }
                    Spacer(modifier = Modifier.weight(0.9f))
                    Icon(
                        imageVector = Icons.Outlined.ArrowForward,
                        contentDescription = "Delete",
                        tint = Color(0xFF8A7488),
                        modifier = Modifier.size(28.dp)
                    )
                }
            }


            Spacer(modifier = Modifier.height(16.dp))

            // Box for Delete Account setting
            Box(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(Color.White)
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(5.dp)
                ) {
                    TextButton(
                        onClick = { showDeleteDialog = true }
                    ) {
                        Text(
                            text = stringResource(id = R.string.delete_account),
                            fontSize = 18.sp
                        )
                    }
                    Spacer(modifier = Modifier.weight(0.9f))
                    Icon(
                        imageVector = Icons.Outlined.Delete,
                        contentDescription = "Delete",
                        tint = Color(0xFF8A7488),
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
        }
    }
}

fun deleteUserAccount(auth: FirebaseAuth, onComplete: (Boolean, String?) -> Unit) {
    val user = auth.currentUser
    user?.delete()?.addOnCompleteListener {
        task ->
        if (task.isSuccessful)
        {
            onComplete(true, null)
        } else
        {
            onComplete(false, task.exception?.message)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksHeaderSetting(title: String, onBackClicked: () -> Boolean) {

    Column {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Card(
                modifier = Modifier
                    .weight(0.18f)
                    .padding(7.dp),
                shape = RoundedCornerShape(20),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 16.dp
                ),
                onClick = {
                    onBackClicked.invoke()
                }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.custom_arrow_icon),
                    contentDescription = "profile picture",
                    modifier = Modifier
                        .size(40.dp)
                        .align(Alignment.CenterHorizontally),
                    contentScale = ContentScale.Crop,
                )
            }
            Text(
                title,
                modifier = Modifier
                    .weight(0.8f)
                    .fillMaxWidth()
                    .padding(end = 60.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Navy,
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.height(10.dp))


    }
}

private const val TAG = "SettingsScreen"

fun changeAppLanguage(context: Context, language: String) {
    val locale = when (language) {
        "Arabic" -> Locale("ar")
        "English" -> Locale("en")
        "Spanish" -> Locale("es")
        "French" -> Locale("fr")
        "German" -> Locale("de")
        else -> Locale.getDefault()
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        val localeManager = context.getSystemService(LocaleManager::class.java)
        localeManager?.applicationLocales = LocaleList.forLanguageTags(locale.toLanguageTag())
    } else {
        AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(locale.toLanguageTag()))
    }

    // Reload the activity to apply the language change
    (context as? Activity)?.recreate()
}


fun getCurrentAppLanguage(context: Context): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        val currentAppLocales = context.getSystemService(LocaleManager::class.java)?.applicationLocales
        Log.d(TAG, "Current App Locales (API 33+): $currentAppLocales")
        currentAppLocales?.get(0)?.displayName ?: "English"
    } else {
        val currentAppLocales = AppCompatDelegate.getApplicationLocales()
        Log.d(TAG, "Current App Locales: $currentAppLocales")
        currentAppLocales[0]?.displayName ?: "English"
    }
}

