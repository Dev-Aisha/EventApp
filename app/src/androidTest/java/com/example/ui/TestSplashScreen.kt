package com.example.ui

import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.DialogNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.eventapp.MainActivity
import com.example.eventapp.component.BottomBar
import com.example.eventapp.navigation.EventsAppNavigation
import com.example.eventapp.navigation.Screens
import com.example.eventapp.screens.auth.AuthViewModel
import com.example.eventapp.screens.auth.SplashScreen
import com.example.eventapp.ui.theme.EventAppTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@HiltAndroidTest
class TestSplashScreen {
    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var composeTestRule = createComposeRule()

    private lateinit var navController: TestNavHostController

    @Before
    fun init(){
        hiltRule.inject()
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            SplashScreen(navController = navController)

            }

        }

    @Test
    fun testSplashScreen(){
        composeTestRule.onNodeWithTag("SplashScreen").assertIsDisplayed()
    }

    @Test
    fun testSplashScreenContent() {
        composeTestRule.onNodeWithTag("intro image").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("title text").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("description text").assertIsDisplayed()


    }

    @Test
    fun testSplashScreenClickbutton() {
        composeTestRule.onNodeWithTag("Login Button").assertIsDisplayed()
        composeTestRule.onNodeWithTag("Login Button").assertHasClickAction()

    }
}