package com.erdum.financetracker.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.erdum.financetracker.models.DayExpenses
import com.erdum.financetracker.ui.theme.GradientText
import com.erdum.financetracker.ui.theme.LabelSecondary
import com.erdum.financetracker.ui.theme.Typography
import com.erdum.financetracker.utils.formatDay
import java.text.DecimalFormat
import java.time.LocalDate

@Composable
fun ExpensesDayGroup(
  date: LocalDate,
  dayExpenses: DayExpenses,
  modifier: Modifier = Modifier
) {
  Column(modifier = modifier) {
    Text(
      date.formatDay(),
      style = Typography.headlineMedium,
      color = LabelSecondary
    )
    Divider(modifier = Modifier.padding(top = 10.dp, bottom = 4.dp))
    dayExpenses.expenses.forEach { expense ->
      ExpenseRow(
        expense = expense,
        modifier = Modifier.padding(top = 12.dp)
      )
    }
    Divider(modifier = Modifier.padding(top = 16.dp, bottom = 4.dp))
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      GradientText(
        text = "Total:",
        style = TextStyle(
          fontFamily = FontFamily.Default,
          fontWeight = FontWeight.ExtraBold,
          fontSize = 17.sp,
          lineHeight = 22.sp
        ))

      Text(
        text = DecimalFormat("Euro 0.#").format(dayExpenses.total),
        style = TextStyle(
          fontFamily = FontFamily.Default,
          fontWeight = FontWeight.ExtraBold,
          fontSize = 17.sp,
          lineHeight = 22.sp
        ), color = Color(0xFF117621)
      )

    }
  }
}