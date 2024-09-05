package com.example.streemapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.compose.StreemAppTheme
import com.example.streemapp.Screen.LoginScreen
import com.example.streemapp.Screen.RegisterScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StreemAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "auth") {
                    navigation(
                        startDestination = "login",
                        route = "auth"
                    ) {
                        composable("login") {
                            val viewModel = it.shareViewModel<SampleViewModel>(navController)
                            LoginScreen(
                                onLoginClick = {
                                    navController.navigate("home") {
                                        popUpTo("auth") {
                                            inclusive = true
                                        }
                                    }
                                },
                                onRegisterClick = {
                                    navController.navigate("register")
                                },
                                viewModel = viewModel
                            )
                        }
                        composable("register") {
                            val viewModel = it.shareViewModel<SampleViewModel>(navController)
                            RegisterScreen(
                                onLoginClick = {
                                    navController.navigate("login")
                                },
                                onRegisterClick = {
                                    navController.navigate("home") {
                                        popUpTo("auth") {
                                            inclusive = true
                                        }
                                    }
                                },
                                viewModel = viewModel
                            )
                        }
                    }
                    composable("home") {
                        // Home screen content here
                    }
                }
            }
        }
    }

    // Helper function
    @Composable
    inline fun <reified T : ViewModel> NavBackStackEntry.shareViewModel(navController: NavController): T {
        val navGraphRoute = destination.parent?.route ?: return viewModel()
        val parentEntry = remember(this) {
            navController.getBackStackEntry(navGraphRoute)
        }
        return viewModel(parentEntry)
    }
}
