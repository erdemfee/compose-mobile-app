package com.erdum.financetracker.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.erdum.financetracker.components.TableRow
import com.erdum.financetracker.db
import com.erdum.financetracker.models.Category
import com.erdum.financetracker.models.Expense
import com.erdum.financetracker.ui.theme.BackgroundElevated
import com.erdum.financetracker.ui.theme.DividerColor
import com.erdum.financetracker.ui.theme.GradientText
import com.erdum.financetracker.ui.theme.Shapes
import com.erdum.financetracker.ui.theme.TopAppBarBackground
import io.realm.kotlin.ext.query
import kotlinx.coroutines.launch
import com.erdum.financetracker.ui.theme.Typography
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Settings(navController: NavController) {
  val coroutineScope = rememberCoroutineScope()
  var deleteConfirmationShowing by remember {
    mutableStateOf(false)
  }

  val eraseAllData: () -> Unit = {
    coroutineScope.launch {
      db.write {
        val expenses = this.query<Expense>().find()
        val categories = this.query<Category>().find()

        delete(expenses)
        delete(categories)

        deleteConfirmationShowing = false
      }
    }
  }

  Scaffold(
    topBar = {
      MediumTopAppBar(
        title = { GradientText(
          text = "Settings",
          style = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 24.sp,
            lineHeight = 22.sp
          ),
          modifier = Modifier.padding(horizontal = 16.dp)
        ) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
          containerColor = TopAppBarBackground
        )
      )
    },
    content = { innerPadding ->
      Column(modifier = Modifier.padding(innerPadding)) {
        Column(
          modifier = Modifier
            .padding(16.dp)
            .clip(Shapes.large)
            .background(BackgroundElevated)
            .fillMaxWidth()
        ) {
          TableRow(
            label = "Categories",
            hasArrow = true,
            modifier = Modifier.clickable {
              navController.navigate("settings/categories")
            })
          Divider(
            modifier = Modifier
              .padding(start = 16.dp), thickness = 1.dp, color = DividerColor
          )
//
          TableRow(
            modifier = Modifier.clickable {
              deleteConfirmationShowing = true
            }
          ) {
            Text(
              text = "Erase all data",
              color = Color.Red, // Set color to red
              style = Typography.bodyMedium,
              modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
            )
          }



          if (deleteConfirmationShowing) {
            AlertDialog(
              onDismissRequest = { deleteConfirmationShowing = false },
              title = { Text("Are you sure?") },
              text = { Text("This action cannot be undone.") },
              confirmButton = {
                TextButton(onClick = eraseAllData) {
                  Text("Delete everything")
                }
              },
              dismissButton = {
                TextButton(onClick = { deleteConfirmationShowing = false }) {
                  Text("Cancel")
                }
              }
            )
          }
        }
      }
    }
  )
}