package com.example.technova3.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.technova3.R
import com.example.technova3.ui.components.BottomNavBar

@Composable
fun ProfileScreen(navController: NavController) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    Scaffold(
        bottomBar = { com.example.technova3.ui.screens.BottomNavBar(navController) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF1E293B))
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            if (isLandscape) {
                // Landscape layout: Profile details in a row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    ProfileImage(140.dp)
                    Spacer(modifier = Modifier.width(24.dp))
                    ProfileInfo(navController, isLandscape)
                }
            } else {
                // Portrait layout: Profile details in a column
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    ProfileImage(120.dp)
                    Spacer(modifier = Modifier.height(16.dp))
                    ProfileInfo(navController, isLandscape)
                }
            }
        }
    }
}

@Composable
fun ProfileImage(size: Dp) {
    Image(
        painter = painterResource(id = R.drawable.pic),
        contentDescription = "User Profile Picture",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(size)
            .clip(CircleShape)
    )
}

@Composable
fun ProfileInfo(navController: NavController, isLandscape: Boolean) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Vishwa De Silva",
            fontSize = if (isLandscape) 24.sp else 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "vishwa@gmail.com",
            fontSize = if (isLandscape) 18.sp else 16.sp,
            color = Color(0xFF60A5FA),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {
                navController.navigate("login") {
                    popUpTo("profile") { inclusive = true }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF60A5FA)),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Log Out", color = Color.White)
        }
    }
}
