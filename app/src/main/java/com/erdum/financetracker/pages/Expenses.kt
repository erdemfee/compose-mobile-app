package com.erdum.financetracker.pages
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.erdum.financetracker.components.PickerTrigger
import com.erdum.financetracker.components.expensesList.ExpensesList
import com.erdum.financetracker.models.Recurrence
import com.erdum.financetracker.ui.theme.GradientText
import com.erdum.financetracker.ui.theme.LabelSecondary
import com.erdum.financetracker.ui.theme.TopAppBarBackground
import com.erdum.financetracker.ui.theme.Typography
import com.erdum.financetracker.viewmodels.ExpensesViewModel
import java.text.DecimalFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Expenses(
  navController: NavController,
  vm: ExpensesViewModel = viewModel()
) {
  val recurrences = listOf(
    Recurrence.Daily,
    Recurrence.Weekly,
    Recurrence.Monthly,
    Recurrence.Yearly
  )

  val state by vm.uiState.collectAsState()
  var recurrenceMenuOpened by remember {
    mutableStateOf(false)
  }

  Scaffold(
    topBar = {
      MediumTopAppBar(
        title = {
          GradientText(text ="Finance Tracking" , style = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 24.sp,
            lineHeight = 22.sp
          ))
         },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
          containerColor = TopAppBarBackground
        )
      )
    },
    content = { innerPadding ->
      Column(
        modifier = Modifier
          .padding(innerPadding)
          .padding(horizontal = 16.dp)
          .padding(top = 16.dp)
          .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          GradientText(
            text = "Spent :",
            style = Typography.bodyMedium
          )
          Spacer(modifier = Modifier.width(75.dp)) // Add space here

          PickerTrigger(
            state.recurrence.target ?: Recurrence.None.target,
            onClick = { recurrenceMenuOpened = !recurrenceMenuOpened },
            modifier = Modifier.padding(start = 16.dp)
          )
          DropdownMenu(expanded = recurrenceMenuOpened,
            onDismissRequest = { recurrenceMenuOpened = false }) {
            recurrences.forEach { recurrence ->
              DropdownMenuItem(text = { GradientText(
                text = recurrence.target,
                style = Typography.bodySmall
              ) }, onClick = {
                vm.setRecurrence(recurrence)
                recurrenceMenuOpened = false
              })
            }
          }
        }
        Row(modifier = Modifier.padding(vertical = 32.dp)) {
          GradientText(
            text =  "€",
            style = Typography.bodyMedium,
            modifier = Modifier.padding(end = 4.dp, top = 4.dp)
          )
          GradientText(
            text = DecimalFormat("0.#").format(state.sumTotal),
            style = TextStyle(
              fontFamily = FontFamily.Default,
              fontWeight = FontWeight.ExtraBold,
              fontSize = 28.sp,
              lineHeight = 34.sp
            )
          )

        }
        ExpensesList(
          expenses = state.expenses,
          modifier = Modifier
            .weight(1f)
            .verticalScroll(
              rememberScrollState()
            )
        )
      }
    }
  )
}

