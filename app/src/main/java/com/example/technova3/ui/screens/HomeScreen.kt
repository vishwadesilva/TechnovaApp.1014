package com.example.technova3.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.technova3.R

// Define color scheme
val DarkBlue = Color(0xFF111827)
val MediumBlue = Color(0xFF1E3A8A)
val LightBlue = Color(0xFF60A5FA)
val TextWhite = Color(0xFFF9FAFB)
val TextGray = Color(0xFFCBD5E1)
val NavBarBlue = Color(0xFF1E293B)
val SlightlyLighterDarkBlue = Color(0xFF1A2333) // Lighten background for contrast

@Composable
fun TopNavBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(NavBarBlue)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.topmenu),
            contentDescription = "Menu",
            modifier = Modifier.size(60.dp) // Adjust size if needed
        )
        Image(
            painter = painterResource(id = R.drawable.logo), // Replace with actual logo if available
            contentDescription = "Technova Logo",
            modifier = Modifier.size(90.dp) // Adjust to fit design
        )
        Image(
            painter = painterResource(id = R.drawable.cart1),
            contentDescription = "Cart",
            modifier = Modifier.size(60.dp) // Adjust size if needed
        )
    }
}
@Composable
fun BottomNavBar(navController: NavController) {
    NavigationBar(containerColor = NavBarBlue) {
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.homeicon),
                    contentDescription = "Home",
                    tint = TextWhite,
                    modifier = Modifier.size(32.dp) // Increased size
                )
            },
            label = { Text("Home", color = TextWhite) },
            selected = false,
            onClick = {navController.navigate("home") {
                popUpTo("home") { inclusive = true }}}
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Profile",
                    tint = TextWhite,
                    modifier = Modifier.size(32.dp) // Increased size
                )
            },
            label = { Text("Profile", color = TextWhite) },
            selected = false,
            onClick = {navController.navigate("ProfileScreen") {
                popUpTo("ProfileScreen") { inclusive = true }}}
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.setting),
                    contentDescription = "Settings",
                    tint = TextWhite,
                    modifier = Modifier.size(32.dp) // Increased size
                )
            },
            label = { Text("Settings", color = TextWhite) },
            selected = false,
            onClick = {
                navController.navigate("settings") {
                    popUpTo("settings") { inclusive = true }
                }
            }
        )

    }
}

@Composable
fun HomeScreen(navController: NavHostController) {
    var selectedCard by remember { mutableStateOf(1) } // Track selected card

    LazyColumn( // ✅ This makes the entire screen scrollable
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBlue),
        verticalArrangement = Arrangement.spacedBy(16.dp) // Adds spacing between sections
    ) {
        item { TopNavBar() }

        item {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    "          Build A PC, Your Way!",
                    style = MaterialTheme.typography.headlineMedium,
                    color = TextWhite
                )
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.customize), // Replace with your actual image
                        contentDescription = "Categories",
                        modifier = Modifier
                            .size(75.dp) // Adjust size if needed
                            .clickable { navController.navigate("categories") } // 📌 This makes it clickable!
                    )
                    Image(
                        painter = painterResource(id = R.drawable.mouse),
                        contentDescription = "Mouse",
                        modifier = Modifier.size(75.dp) // Adjust size if needed
                    )
                    Image(
                        painter = painterResource(id = R.drawable.notsure), // Replace with actual image
                        contentDescription = "Shop",
                        modifier = Modifier
                            .size(75.dp)
                            .clickable {
                                navController.navigate("shop") // Navigates to the Shop screen
                            }
                    )

                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        // ✅ Scrollable Cards Section
        item {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                item {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        OrderCard(
                            imageRes = R.drawable.orderimg,
                            title = "Order Assembled PC",
                            isFront = selectedCard == 1,
                            onClick = { selectedCard = 1 }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = { navController.navigate("shop") }, // ✅ Navigate to ShopScreen
                            colors = ButtonDefaults.buttonColors(containerColor = LightBlue)
                        ) {
                            Text("Browse", color = Color.White)
                        }
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        OrderCard(
                            imageRes = R.drawable.custom1,
                            title = "Customize Your PC",
                            isFront = selectedCard == 2,
                            onClick = { selectedCard = 2 }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = { /* TODO: Navigate to customization */ },
                            colors = ButtonDefaults.buttonColors(containerColor = LightBlue)
                        ) {
                            Text("Customize", color = Color.White)
                        }
                    }
                }
            }
        }

        // ✅ "About Us" Section (Now scrollable!)
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(SlightlyLighterDarkBlue)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "About Us",
                    style = MaterialTheme.typography.headlineSmall,
                    color = TextWhite
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "Technova is your one-stop shop for building the perfect PC. Whether you're a gamer, a creator, or a professional, we offer a seamless experience for customizing and ordering high-performance desktops tailored to your needs.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextGray,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))


            }
        }

        // ✅ Bottom Navigation (Still visible at the bottom)
        item { BottomNavBar(navController) }
    }
}



@Composable
fun OrderCard(imageRes: Int, title: String, isFront: Boolean, onClick: () -> Unit, isCustomize: Boolean = false) {
    val elevation by animateFloatAsState(if (isFront) 12f else 2f)
    val opacity by animateFloatAsState(if (isFront) 1f else 0.5f)

    val size = if (isFront) 250.dp else 200.dp // Card size stays the same

    val baseImageSize = if (isCustomize) 180f else 140f // Customize PC image is bigger
    val selectedImageSize = if (isCustomize) 220f else 180f
    val imageSize by animateFloatAsState(if (isFront) selectedImageSize else baseImageSize)

    Card(
        modifier = Modifier
            .size(size)
            .shadow(elevation.dp)
            .clickable { onClick() }
            .graphicsLayer(alpha = opacity),
        colors = CardDefaults.cardColors(containerColor = MediumBlue)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = title,
                modifier = Modifier.size(imageSize.dp), // Bigger image for customize PC
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(title, color = TextWhite, style = MaterialTheme.typography.bodyLarge)
        }
    }
}