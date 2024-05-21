package com.example.eventapp.component

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eventapp.R
import org.w3c.dom.Text


@Composable
fun TaskHeaderView(title: String){

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 16.dp)
        ,verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center) {

        Card(
            modifier = Modifier
                .weight(0.18f)
                .padding(7.dp),
            shape = RoundedCornerShape(20),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 16.dp
            ),
        ){

            Image(painter = painterResource(id = R.drawable.google_image),
                contentDescription ="profile",
                modifier = Modifier.size(40.dp).align(Alignment.CenterHorizontally),
                contentScale = ContentScale.Crop,
                )

        }

        Text(modifier = Modifier
            .weight(0.8f)
            .fillMaxWidth()
            .padding(end= 60.dp),
            text = title,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color.Black,
            fontSize = 20.sp,
        )


    }

}