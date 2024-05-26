package com.erdum.financetracker.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.erdum.financetracker.models.Expense
import com.erdum.financetracker.ui.theme.GradientText
import com.erdum.financetracker.ui.theme.LabelSecondary
import com.erdum.financetracker.ui.theme.Typography
import java.text.DecimalFormat
import java.time.format.DateTimeFormatter

@Composable
fun ExpenseRow(expense: Expense, modifier: Modifier = Modifier) {
  Column(modifier = modifier) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Text(
        expense.note ?: expense.category!!.name,
        style = Typography.headlineMedium
      )
      GradientText(
        text = "Euro ${DecimalFormat("0.#").format(expense.amount)}",
        style =  Typography.headlineMedium
      )

    }
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 6.dp),
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      CategoryBadge(category = expense.category!!)
      GradientText(
        text = expense.date.format(DateTimeFormatter.ofPattern("HH:mm")),
        style = Typography.bodyMedium
      )
    }
  }
}