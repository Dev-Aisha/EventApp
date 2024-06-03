package com.example.eventapp.screens.chart

import android.graphics.Typeface
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberBottomAxis
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberStartAxis
import com.patrykandpatrick.vico.compose.cartesian.decoration.rememberHorizontalLine
import com.patrykandpatrick.vico.compose.cartesian.fullWidth
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberColumnCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.compose.common.component.rememberLineComponent
import com.patrykandpatrick.vico.compose.common.component.rememberShapeComponent
import com.patrykandpatrick.vico.compose.common.component.rememberTextComponent
import com.patrykandpatrick.vico.compose.common.of
import com.patrykandpatrick.vico.core.cartesian.HorizontalLayout
import com.patrykandpatrick.vico.core.cartesian.axis.AxisItemPlacer
import com.patrykandpatrick.vico.core.cartesian.axis.BaseAxis
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.cartesian.data.CartesianValueFormatter
import com.patrykandpatrick.vico.core.cartesian.data.columnSeries
import com.patrykandpatrick.vico.core.cartesian.decoration.HorizontalLine
import com.patrykandpatrick.vico.core.cartesian.layer.ColumnCartesianLayer
import com.patrykandpatrick.vico.core.common.Dimensions
import com.patrykandpatrick.vico.core.common.component.LineComponent
import com.patrykandpatrick.vico.core.common.component.ShapeComponent
import com.patrykandpatrick.vico.core.common.component.TextComponent
import com.patrykandpatrick.vico.core.common.shape.Shape
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.withContext
import java.text.DateFormatSymbols
import java.time.LocalDate
import java.util.Locale
import kotlin.random.Random


@Composable
internal fun MonthChar(modifier: Modifier) {
    val modelProducer = remember { CartesianChartModelProducer.build() }
    LaunchedEffect(Unit) {
        withContext(Dispatchers.Default) {
            while (isActive) {
                modelProducer.tryRunTransaction {
                    /* Learn more:
                    https://patrykandpatrick.com/vico/wiki/cartesian-charts/layers/column-layer#data. */
                    columnSeries {
                        series(
                            List(47)
                        {
                            2
                        })
                    }

                }
                delay(Defaults.TRANSACTION_INTERVAL_MS)
            }
        }
    }
    MonthChartScreen(modelProducer, modifier)
}

@Composable
private fun MonthChartScreen(modelProducer: CartesianChartModelProducer, modifier: Modifier) {
    Card(
        modifier = Modifier.padding(13.dp),
        colors = CardDefaults.cardColors(Color.White),
    )
    {
        CartesianChartHost(
            chart =
            rememberCartesianChart(
                rememberColumnCartesianLayer(
                    ColumnCartesianLayer.ColumnProvider.series(
                        rememberLineComponent(
                            color = Color(0xFF8CC28D),
                            thickness = 16.dp,
                            shape = remember { Shape.rounded(allPercent = 40) },
                        )
                    )
                ),
                startAxis = rememberStartAxis(),
                bottomAxis =
                rememberBottomAxis(
                    valueFormatter = bottomAxisValueFormatter,
                    itemPlacer =
                    remember {
                        AxisItemPlacer.Horizontal.default(
                            spacing = 1,
                            addExtremeLabelPadding = true
                        )
                    },
                ),
                decorations = listOf(rememberComposeHorizontalLine()),
            ),
            modelProducer = modelProducer,
            modifier = modifier,
            marker = rememberMarker(),
            horizontalLayout = HorizontalLayout.fullWidth(),
        )
    }
}


@Composable
private fun rememberComposeHorizontalLine(): HorizontalLine {
    val color = Color(HORIZONTAL_LINE_COLOR)
    return rememberHorizontalLine(
        y = { HORIZONTAL_LINE_Y },
        line = rememberLineComponent(color, HORIZONTAL_LINE_THICKNESS_DP.dp),
        labelComponent =
        rememberTextComponent(
            background = rememberShapeComponent(Shape.Pill, color),
            padding =
            Dimensions.of(
                HORIZONTAL_LINE_LABEL_HORIZONTAL_PADDING_DP.dp,
                HORIZONTAL_LINE_LABEL_VERTICAL_PADDING_DP.dp,
            ),
            margins = Dimensions.of(HORIZONTAL_LINE_LABEL_MARGIN_DP.dp),
            typeface = Typeface.MONOSPACE,
        ),
    )
}



private const val HORIZONTAL_LINE_Y = 14f
private const val HORIZONTAL_LINE_COLOR = -2893786
private const val HORIZONTAL_LINE_THICKNESS_DP = 2f
private const val HORIZONTAL_LINE_LABEL_HORIZONTAL_PADDING_DP = 8f
private const val HORIZONTAL_LINE_LABEL_VERTICAL_PADDING_DP = 2f
private const val HORIZONTAL_LINE_LABEL_MARGIN_DP = 4f

private val monthNames = run {
    val originalMonthNames = DateFormatSymbols.getInstance(Locale.US).shortMonths
    val currentMonth = LocalDate.now().month.value - 1
    originalMonthNames.drop(currentMonth) + originalMonthNames.take(currentMonth)
}
private val bottomAxisValueFormatter = CartesianValueFormatter { x, _, _ ->
    "${monthNames[x.toInt() % 12]}"
}