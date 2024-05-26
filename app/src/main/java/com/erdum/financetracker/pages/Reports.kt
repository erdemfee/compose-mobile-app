package com.erdum.financetracker.pages

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.erdum.financetracker.components.ReportPage
import com.erdum.financetracker.models.Recurrence
import com.erdum.financetracker.ui.theme.TopAppBarBackground
import com.erdum.financetracker.viewmodels.ReportsViewModel
import com.erdum.financetracker.R
import com.erdum.financetracker.ui.theme.GradientText
import com.erdum.financetracker.ui.theme.TextPrimary
import kotlin.text.Typography

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun Reports(vm: ReportsViewModel = viewModel()) {
  val uiState = vm.uiState.collectAsState().value

  val recurrences = listOf(
    Recurrence.Weekly,
    Recurrence.Monthly,
    Recurrence.Yearly
  )

  Scaffold(
    topBar = {
      MediumTopAppBar(
        title = { GradientText(text = "Report", style = TextStyle(
          fontFamily = FontFamily.Default,
          fontWeight = FontWeight.ExtraBold,
          fontSize = 24.sp,
          lineHeight = 22.sp
        )
        )},
        colors = TopAppBarDefaults.mediumTopAppBarColors(
          containerColor = TopAppBarBackground
        ),
        actions = {
          IconButton(onClick = vm::openRecurrenceMenu) {
            Icon(
              painterResource(id = R.drawable.ic_today),
              contentDescription = "Change recurrence"
            )
          }

          DropdownMenu(
            expanded = uiState.recurrenceMenuOpened,
            onDismissRequest = vm::closeRecurrenceMenu
          ) {

            recurrences.forEach { recurrence ->
              DropdownMenuItem(text = { GradientText(text = recurrence.name, style = TextStyle(
                fontFamily = FontFamily.Default,
              )) }, onClick = {
                vm.setRecurrence(recurrence)
                vm.closeRecurrenceMenu()
              })
            }
          }
        }
      )
    },
    content = { innerPadding ->
      val numOfPages = when (uiState.recurrence) {
        Recurrence.Weekly -> 53
        Recurrence.Monthly -> 12
        Recurrence.Yearly -> 1
        else -> 53
      }
      HorizontalPager(count = numOfPages, reverseLayout = true) { page ->
        ReportPage(innerPadding, page, uiState.recurrence)
      }
    }
  )
}