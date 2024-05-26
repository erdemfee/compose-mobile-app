package com.erdum.financetracker.components.expensesList
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.erdum.financetracker.components.ExpensesDayGroup
import com.erdum.financetracker.models.Expense
import com.erdum.financetracker.models.groupedByDay
import com.erdum.financetracker.ui.theme.GradientText

@Composable
fun ExpensesList(expenses: List<Expense>, modifier: Modifier = Modifier) {
  val groupedExpenses = expenses.groupedByDay()

  Column(modifier = modifier) {
    if (groupedExpenses.isEmpty()) {
      GradientText("You didn't spent any money yet!",style= (com.erdum.financetracker.ui.theme.Typography.bodySmall), modifier = Modifier.padding(top = 32.dp))
    } else {
      groupedExpenses.keys.forEach { date ->
        if (groupedExpenses[date] != null) {
          ExpensesDayGroup(
            date = date,
            dayExpenses = groupedExpenses[date]!!,
            modifier = Modifier.padding(top = 24.dp)
          )
        }
      }
    }
  }
}
