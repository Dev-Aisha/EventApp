package com.example.eventapp.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.eventapp.R

@Composable
fun TagsCard() {

    Box(
        modifier = Modifier
            .requiredSize(size = 138.dp)
            .clip(shape = RoundedCornerShape(14.dp))
            .background(color = Color(0xff858fe9).copy(alpha = 0.25f))

    )
    {
        Column(
            modifier = Modifier.padding(vertical = 26.dp, horizontal = 35.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,){

            Box(
                modifier = Modifier
                    .requiredSize(size = 48.dp)
                    .clip(shape = RoundedCornerShape(14.dp))
                    .background(color = Color(0xff858fe9)))
            {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "Iconly/Curved/Profile",
                    modifier = Modifier
                        .fillMaxSize())
            }
            Text(
                text = "Personal",
                color = Color(0xff10275a),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium),)
            Text(
                text = "6 Task",
                color = Color(0xff12175d).copy(alpha = 0.5f),
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium),)

        }
    }

}