package com.erdum.financetracker
//
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.erdum.financetracker.pages.*
import com.erdum.financetracker.ui.theme.FinanceTrackerTheme
import io.sentry.compose.withSentryObservableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

class MainActivity : ComponentActivity() {
  @OptIn(ExperimentalMaterial3Api::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
    setContent {
      FinanceTrackerTheme {
        var showBottomBar by rememberSaveable { mutableStateOf(true) }
        val navController = rememberNavController().withSentryObservableEffect()
        val backStackEntry by navController.currentBackStackEntryAsState()

        showBottomBar = when (backStackEntry?.destination?.route) {
          "settings/categories" -> false
          else -> true
        }

        Scaffold(
          bottomBar = {
            if (showBottomBar) {
              NavigationBar(containerColor = Color.White) {
                NavigationBarItem(
                  selected = backStackEntry?.destination?.route == "expenses",
                  onClick = { navController.navigate("expenses") },
                  label = { Text("Finance") },
                  icon = {
                    Icon(
                      painterResource(id = R.drawable.upload),
                      contentDescription = "Upload"
                    )
                  },
                  colors = NavigationBarItemDefaults.colors(
                    selectedTextColor = Color(0xFF0095FF),
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray,
                    indicatorColor = Color.White // This prevents the hover color
                  )
                )
                NavigationBarItem(
                  selected = backStackEntry?.destination?.route == "reports",
                  onClick = { navController.navigate("reports") },
                  label = { Text("Reports") },
                  icon = {
                    Icon(
                      painterResource(id = R.drawable.bar_chart),
                      contentDescription = "Reports"
                    )
                  },
                  colors = NavigationBarItemDefaults.colors(
                    selectedTextColor = Color(0xFF0095FF),
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray,
                    indicatorColor = Color.White // This prevents the hover color
                  )
                )
                NavigationBarItem(
                  selected = backStackEntry?.destination?.route == "add",
                  onClick = { navController.navigate("add") },
                  label = { Text("Expenses") },
                  icon = {
                    Icon(
                      painterResource(id = R.drawable.add),
                      contentDescription = "Add"
                    )
                  },
                  colors = NavigationBarItemDefaults.colors(
                    selectedTextColor = Color(0xFF0095FF),
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray,
                    indicatorColor = Color.White // This prevents the hover color
                  )
                )
                NavigationBarItem(
                  selected = backStackEntry?.destination?.route?.startsWith("settings")
                    ?: false,
                  onClick = { navController.navigate("settings") },
                  label = { Text("Settings") },
                  icon = {
                    Icon(
                      painterResource(id = R.drawable.settings_outlined),
                      contentDescription = "Settings"
                    )
                  },
                  colors = NavigationBarItemDefaults.colors(
//                    selectedIconColor = Color(0xFF0095FF),
                    selectedTextColor = Color(0xFF0095FF),
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray,
                    indicatorColor = Color.White // This prevents the hover color
                  )
                )
              }
            }
          },
          content = { innerPadding ->
            NavHost(
              navController = navController,
              startDestination = "expenses"
            ) {
              composable("expenses") {
                Surface(
                  modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                ) {
                  Expenses(navController)
                }
              }
              composable("reports") {
                Surface(
                  modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                ) {
                  Reports()
                }
              }
              composable("add") {
                Surface(
                  modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                ) {
                  Add(navController)
                }
              }
              composable("settings") {
                Surface(
                  modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                ) {
                  Settings(navController)
                }
              }
              composable("settings/categories") {
                Surface(
                  modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                ) {
                  Categories(navController)
                }
              }
            }
          }
        )
      }
    }
  }
}
