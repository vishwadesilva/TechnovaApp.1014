package com.example.technova3.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.technova3.R


@Composable
fun CategoriesScreen(navController: NavController) {
    Scaffold(
        bottomBar = { BottomNavBar(navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF111827)) // 🔹 Uses theme background
                .padding(innerPadding)
        ) {
            TopNavBar(title = "Categories")

            val categories = listOf(
                CategoryData(R.drawable.componants, "PC Components", "High-quality PC parts for building or upgrading your system."),
                CategoryData(R.drawable.pre, "Pre Built PC", "Ready-to-use PCs for gaming, work, or everyday use."),
                CategoryData(R.drawable.cases, "Cases and Essentials", "Find high-quality PC cases, cooling systems, and power supplies."),
                CategoryData(R.drawable.accessories, "Accessories", "Get gaming keyboards, mice, headsets, and other accessories.")
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                itemsIndexed(categories) { index, category ->
                    CategoryCard(
                        imageRes = category.imageRes,
                        title = category.title,
                        description = category.description,
                        isImageLeft = index % 2 == 0,
                        navController = navController
                    )
                }
            }
        }
    }
}


data class CategoryData(val imageRes: Int, val title: String, val description: String)

@Composable
fun CategoryCard(
    imageRes: Int,
    title: String,
    description: String,
    isImageLeft: Boolean,
    navController: NavController
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isImageLeft) {
                CategoryImage(imageRes, Modifier.weight(1f))
                Spacer(modifier = Modifier.width(12.dp))
                CategoryText(title, description, Modifier.weight(2f), navController)
            } else {
                CategoryText(title, description, Modifier.weight(2f), navController)
                Spacer(modifier = Modifier.width(12.dp))
                CategoryImage(imageRes, Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun CategoryImage(imageRes: Int, modifier: Modifier) {
    Image(
        painter = painterResource(id = imageRes),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .clip(RoundedCornerShape(12.dp))
    )
}

@Composable
fun CategoryText(title: String, description: String, modifier: Modifier, navController: NavController) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = description,
            color = Color.LightGray,
            fontSize = 14.sp,
            maxLines = 3
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { navController.navigate("shop") },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF60A5FA)),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.align(Alignment.Start)
        ) {
            Text("Check Selection", color = Color.White)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavBar(title: String) {
    TopAppBar(
        title = {
            Text(
                text = title,
                color = Color.White,
                fontSize = 55.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF1E293B))
    )
}
