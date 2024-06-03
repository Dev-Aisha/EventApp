package com.example.eventapp.screens.chart

import android.graphics.Typeface
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eventapp.R
import com.example.eventapp.ui.theme.Navy
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
internal fun ChartScreen(modifier: Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
    ) {

        Image(
            painter = painterResource(id = R.drawable.background_image),
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.4f),
            contentScale = ContentScale.Crop
        )

            Column() {
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    "Your Activity",
                    modifier = Modifier.padding(top = 10.dp).align(alignment = Alignment.CenterHorizontally),
                    fontWeight = FontWeight.Bold,
                    fontSize = 23.sp,
                    color = Navy,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(10.dp))

                Column {
                    LazyColumn() {
                        item {
                            Text(
                                "Day Activity",
                                modifier = Modifier.padding(start = 18.dp),
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = Navy,
                                textAlign = TextAlign.Start
                            )
                            DayChart(modifier)
                        }
                        item {
                            Text(
                                "Month Activity",
                                modifier = Modifier.padding(start = 18.dp),
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = Navy,
                                textAlign = TextAlign.Start
                            )
                            MonthChar(modifier)
                        }
                    }
                }
            }


        }
    }







