package com.erdum.financetracker.components.charts

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.tehras.charts.bar.BarChart
import com.github.tehras.charts.bar.BarChartData
import com.github.tehras.charts.bar.BarChartData.Bar
import com.github.tehras.charts.bar.renderer.yaxis.SimpleYAxisDrawer
import com.erdum.financetracker.models.Expense
import com.erdum.financetracker.models.Recurrence
import com.erdum.financetracker.models.groupedByDayOfWeek
import com.erdum.financetracker.ui.theme.LabelSecondary
import com.erdum.financetracker.utils.simplifyNumber
import java.time.DayOfWeek

@Composable
fun WeeklyChart(expenses: List<Expense>) {
  val groupedExpenses = expenses.groupedByDayOfWeek()

  BarChart(
    barChartData = BarChartData(
      bars = listOf(
        Bar(
          label = DayOfWeek.MONDAY.name.substring(0, 1),
          value = groupedExpenses[DayOfWeek.MONDAY.name]?.total?.toFloat()
            ?: 0f,
          color = Color(0xFF005999),
        ),
        Bar(
          label = DayOfWeek.TUESDAY.name.substring(0, 1),
          value = groupedExpenses[DayOfWeek.TUESDAY.name]?.total?.toFloat() ?: 0f,
          color = Color(0xFF005999)
        ),
        Bar(
          label = DayOfWeek.WEDNESDAY.name.substring(0, 1),
          value = groupedExpenses[DayOfWeek.WEDNESDAY.name]?.total?.toFloat() ?: 0f,
          color = Color(0xFF005999)
        ),
        Bar(
          label = DayOfWeek.THURSDAY.name.substring(0, 1),
          value = groupedExpenses[DayOfWeek.THURSDAY.name]?.total?.toFloat() ?: 0f,
          color = Color(0xFF005999)
        ),
        Bar(
          label = DayOfWeek.FRIDAY.name.substring(0, 1),
          value = groupedExpenses[DayOfWeek.FRIDAY.name]?.total?.toFloat() ?: 0f,
          color = Color(0xFF005999)
        ),
        Bar(
          label = DayOfWeek.SATURDAY.name.substring(0, 1),
          value = groupedExpenses[DayOfWeek.SATURDAY.name]?.total?.toFloat() ?: 0f,
          color = Color(0xFF005999)
        ),
        Bar(
          label = DayOfWeek.SUNDAY.name.substring(0, 1),
          value = groupedExpenses[DayOfWeek.SUNDAY.name]?.total?.toFloat() ?: 0f,
          color = Color(0xFF005999)
        ),
      )
    ),
    labelDrawer = LabelDrawer(recurrence = Recurrence.Weekly),
    yAxisDrawer = SimpleYAxisDrawer(
      labelTextColor = LabelSecondary,
      labelValueFormatter = ::simplifyNumber,
      labelRatio = 7,
      labelTextSize = 14.sp
    ),
    barDrawer = BarDrawer(recurrence = Recurrence.Weekly),
    modifier = Modifier
      .padding(bottom = 20.dp)
      .fillMaxSize()
  )
}