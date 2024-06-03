package com.example.eventapp.screens.chart


import android.graphics.Typeface
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.eventapp.R
import com.example.eventapp.screens.task.TaskViewModel
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberBottomAxis
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberStartAxis
import com.patrykandpatrick.vico.compose.cartesian.decoration.rememberHorizontalBox
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberColumnCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.compose.cartesian.rememberVicoZoomState
import com.patrykandpatrick.vico.compose.common.component.rememberLineComponent
import com.patrykandpatrick.vico.compose.common.component.rememberShapeComponent
import com.patrykandpatrick.vico.compose.common.component.rememberTextComponent
import com.patrykandpatrick.vico.compose.common.of
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.cartesian.data.CartesianValueFormatter
import com.patrykandpatrick.vico.core.cartesian.data.columnSeries
import com.patrykandpatrick.vico.core.cartesian.decoration.HorizontalBox
import com.patrykandpatrick.vico.core.cartesian.layer.ColumnCartesianLayer
import com.patrykandpatrick.vico.core.common.Dimensions
import com.patrykandpatrick.vico.core.common.shape.Shape
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale
import kotlin.random.Random

@Composable
internal fun DayChart(modifier: Modifier) {


    val viewModel: TaskViewModel = hiltViewModel()
    val tasks = viewModel.taskWithTags

    val completedTask = viewModel.completedTasks.collectAsState(initial = null)
    val cancelledTask = viewModel.cancelledTasks.collectAsState(initial = null)
    val onGoingTask = viewModel.onGoingTasks.collectAsState(initial = null)
    val pendingTask = viewModel.pendingTasks.collectAsState(initial = null)

    val completedTaskCount = completedTask.value?.tasks?.size
    val cancelledTaskCount = cancelledTask.value?.tasks?.size
    val onGoingTaskCount = onGoingTask.value?.tasks?.size
    val pendingTaskCount = pendingTask.value?.tasks?.size

    var selectedDate by remember {
        mutableStateOf(LocalDate.now().toString())
    }

    val list = listOf(completedTaskCount,cancelledTaskCount,onGoingTaskCount,pendingTaskCount)


    val groupedList = tasks.value.groupBy {
        it.task.timeFrom
    }



    //start -> left : number of task
    //bottom -> day or week

    val modelProducer = remember { CartesianChartModelProducer.build() }
    LaunchedEffect(Unit) {
        withContext(Dispatchers.Default) {
            while (isActive) {
                modelProducer.tryRunTransaction {
                    /* Learn more:
                    https://patrykandpatrick.com/vico/wiki/cartesian-charts/layers/column-layer#data. */
                    columnSeries {
                        repeat(Defaults.MULTI_SERIES_COUNT) {
                            series(
                                4
                            )
                            series(
                                6
                            )
                            series(
                               2
                            )
                            series(
                                List(Defaults.ENTRY_COUNT) {
                                    1
                                }
                            )

                        }
                    }
                }
                delay(Defaults.TRANSACTION_INTERVAL_MS)
            }
        }
    }
    DayChartScreen(modelProducer)

}

@Composable
fun DayChartScreen(modelProducer: CartesianChartModelProducer) {
    val horizontalBox = rememberComposeHorizontalBox()
    val shape = remember { Shape.cut(topLeftPercent = 50) }



        Card(
            modifier = Modifier.padding(13.dp),
            colors= cardColors(Color.White),
        )
        {

            CartesianChartHost(
                chart =
                rememberCartesianChart(
                    rememberColumnCartesianLayer(
                        ColumnCartesianLayer.ColumnProvider.series(
                            columnColors.map {
                                rememberLineComponent(
                                    color = it,
                                    thickness = 8.dp,
                                    shape = shape
                                )
                            }
                        )
                    ),
                    startAxis = rememberStartAxis(),
                    bottomAxis = rememberBottomAxis(valueFormatter = bottomAxisValueFormatter),
                    decorations = remember(horizontalBox) { listOf(horizontalBox) },
                ),
                modelProducer = modelProducer,
                modifier = Modifier,
                marker = rememberMarker(),
                runInitialAnimation = false,
                zoomState = rememberVicoZoomState(zoomEnabled = false),
            )
        }



    }


@Composable
private fun rememberComposeHorizontalBox(): HorizontalBox {
    val color = Color(HORIZONTAL_BOX_COLOR)
    return rememberHorizontalBox(
        y = { horizontalBoxY },
        box = rememberShapeComponent(color = color.copy(HORIZONTAL_BOX_ALPHA)),
        labelComponent =
        rememberTextComponent(
            background = rememberShapeComponent(Shape.Rectangle, color),
            padding =
            Dimensions.of(
                HORIZONTAL_BOX_LABEL_HORIZONTAL_PADDING_DP.dp,
                HORIZONTAL_BOX_LABEL_VERTICAL_PADDING_DP.dp,
            ),
            margins = Dimensions.of(HORIZONTAL_BOX_LABEL_MARGIN_DP.dp),
            typeface = Typeface.MONOSPACE,
        ),
    )
}



private const val HORIZONTAL_BOX_COLOR = -1448529
private const val HORIZONTAL_BOX_ALPHA = .36f
private const val HORIZONTAL_BOX_LABEL_HORIZONTAL_PADDING_DP = 8f
private const val HORIZONTAL_BOX_LABEL_VERTICAL_PADDING_DP = 2f
private const val HORIZONTAL_BOX_LABEL_MARGIN_DP = 4f

private val columnColors = listOf(Color(0xFFB8B1F0), Color(0xFF8CC28D), Color(0xFF66B3D3), Color(0xFFF7AABA))
private val horizontalBoxY = 7f..14f
private val daysOfWeek = run {
    val originalDaysOfWeek = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
    val currentDayOfWeek = LocalDate.now().dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH)
    val currentDayIndex = originalDaysOfWeek.indexOf(currentDayOfWeek)
    if (currentDayIndex != -1) {
        originalDaysOfWeek.drop(currentDayIndex) + originalDaysOfWeek.take(currentDayIndex)
    } else {
        originalDaysOfWeek
    }
}
private val bottomAxisValueFormatter = CartesianValueFormatter { x, _, _ ->
    daysOfWeek[x.toInt() % daysOfWeek.size]
}