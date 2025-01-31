package com.example.technova3.ui.screens



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.technova3.ui.theme.TechnovaTheme

import androidx.compose.runtime.Composable

@Composable
fun SettingsScreen(navController: NavHostController) {
    var isDarkMode by rememberSaveable { mutableStateOf(false) }

    TechnovaTheme(darkTheme = isDarkMode) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Settings",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { isDarkMode = true },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Text("Enable Dark Mode", color = Color.White)
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = { isDarkMode = false },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text("Enable Light Mode", color = Color.Black)
            }

            Spacer(modifier = Modifier.height(20.dp)) // Space before the Home button

            // ✅ Home button to navigate back
            Button(
                onClick = { navController.navigate("home") }, // Replace "home" with your actual HomeScreen route
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text("Go to Home", color = MaterialTheme.colorScheme.onPrimary)
            }
        }
    }
}
