package com.ammar.thmanyah

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ammar.thmanyah.core.ui.theme.ThmanyahTaskTheme
import com.ammar.thmanyah.navigation.HomeRoutes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            ThmanyahTaskTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = { BottomNavigationBar(navController) }) { innerPadding ->
                    NavigationHost(navController, innerPadding)
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val selectTab = remember { mutableStateOf<HomeRoutes>(HomeRoutes.Home) }
    val items = listOf(HomeRoutes.Home, HomeRoutes.Search)

    val selectedColor = MaterialTheme.colorScheme.onSurface
    val unselectedColor = MaterialTheme.colorScheme.onSurfaceVariant

    NavigationBar(
        modifier = Modifier.height(80.dp),
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = selectedColor,
        tonalElevation = 0.dp,
    ) {
        items.forEachIndexed { index, screen ->
            NavigationBarItem(
                label = {
                    Text(
                        text = screen.title,
                        fontSize = 12.sp,
                        fontWeight = if (screen == selectTab.value) FontWeight.Medium else FontWeight.Normal,
                    )
                },
                selected = screen == selectTab.value,
                onClick = {
                    selectTab.value = screen
                    navController.navigate(screen.route)
                },
                icon = {
                    Icon(
                        imageVector =
                            when {
                                index == 0 && screen == selectTab.value -> Icons.Filled.Home
                                index == 0 -> Icons.Outlined.Home
                                screen == selectTab.value -> Icons.Filled.Search
                                else -> Icons.Outlined.Search
                            },
                        contentDescription = screen.title,
                        modifier =
                            Modifier
                                .size(24.dp)
                                .padding(top = 4.dp),
                    )
                },
                colors =
                    NavigationBarItemDefaults.colors(
                        selectedIconColor = selectedColor,
                        unselectedIconColor = unselectedColor,
                        selectedTextColor = selectedColor,
                        unselectedTextColor = unselectedColor,
                    ),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    ThmanyahTaskTheme {
        val navController = rememberNavController()
        BottomNavigationBar(navController)
    }
}