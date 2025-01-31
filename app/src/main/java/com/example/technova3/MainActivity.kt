package com.example.technova3

import com.example.technova3.ui.screens.SettingsScreen
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import com.example.technova3.ui.screens.*
import com.example.technova3.ui.theme.Technova3Theme

class MainActivity : ComponentActivity() {
    @SuppressLint("ComposableDestinationInComposeScope")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Technova3Theme {
                val navController = rememberNavController() // ✅ Navigation controller

                Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        NavHost(navController = navController, startDestination = "login") {
                            composable("login") {
                                LoginScreen(
                                    onSignInClick = { navController.navigate("home") },
                                    onSignUpClick = { navController.navigate("register") }
                                )
                            }
                            composable("register") {
                                RegisterScreen(
                                    onRegisterClick = { navController.navigate("home") },
                                    onLoginClick = { navController.navigate("login") }
                                )
                            }
                            composable("categories") {
                                CategoriesScreen(navController) // ✅ Corrected
                            }
                            composable("shop") {
                                ShopScreen(navController) // ✅ Corrected
                            }
                            composable("shop") { // ✅ FIXED: This is now at the right level
                                ShopScreen(navController)
                            }
                            composable("home") {
                                HomeScreen(navController) // ✅ Corrected
                            }
                            composable("settings") {
                                SettingsScreen(navController) // ✅ Corrected
                            }
                            composable("ProfileScreen") {
                                ProfileScreen(navController) // ✅ Corrected

                        }
                    }
                }
            }
        }
    }
}}
