package com.example.technova3.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.technova3.R
import com.example.technova3.ui.components.BottomNavBar

@Composable
fun ShopScreen(navController: NavController) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.screenWidthDp > configuration.screenHeightDp // 🔹 Detects landscape mode

    Scaffold(
        bottomBar = { com.example.technova3.ui.screens.BottomNavBar(navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF111827))
                .padding(paddingValues)
        ) {
            TopBar(isLandscape) // 🔹 Uses responsive TopBar
            Spacer(modifier = Modifier.height(16.dp))
            ProductGrid()
        }
    }
}

@Composable
fun TopBar(isLandscape: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = if (isLandscape) 4.dp else 12.dp), // 🔹 Smaller padding in landscape
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = { /* Open filter */ }, modifier = Modifier.size(if (isLandscape) 32.dp else 48.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.filter),
                contentDescription = "Filter",
                tint = Color.White,
                modifier = Modifier.size(if (isLandscape) 24.dp else 36.dp) // 🔹 Smaller icon in landscape
            )
        }
        Text(
            text = "Shop",
            fontSize = if (isLandscape) 32.sp else 55.sp, // 🔹 Smaller title in landscape
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        IconButton(onClick = { /* Open cart */ }, modifier = Modifier.size(if (isLandscape) 32.dp else 48.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.cart),
                contentDescription = "Cart",
                tint = Color.White,
                modifier = Modifier.size(if (isLandscape) 24.dp else 36.dp) // 🔹 Smaller icon in landscape
            )
        }
    }
}

@Composable
fun ProductGrid() {
    val products = List(6) { ProductData(R.drawable.shoppic, "Logitech G502 Lightspeed Mouse", "Rs. 6,000/-") }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(products) { product ->
            ProductCard(product)
        }
    }
}

data class ProductData(val imageRes: Int, val title: String, val price: String)

@Composable
fun ProductCard(product: ProductData) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(12.dp))
            ) {
                Image(
                    painter = painterResource(id = product.imageRes),
                    contentDescription = product.title,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = product.title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = product.price,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF60A5FA)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { /* Add to cart */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF60A5FA)),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text("Add to Cart", color = Color.White, fontSize = 12.sp)
            }
        }
    }
}
