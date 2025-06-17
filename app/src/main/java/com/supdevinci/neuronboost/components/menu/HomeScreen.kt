package com.supdevinci.neuronboost.components.menu

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.ui.platform.LocalContext
import com.supdevinci.neuronboost.R
import com.supdevinci.neuronboost.ui.theme.AppColors
import com.supdevinci.neuronboost.ui.theme.buttonPlay
import com.supdevinci.neuronboost.view.MenuActivity
import androidx.compose.foundation.shape.RoundedCornerShape


@Composable
fun HomeScreen() {
    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.homebackground),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Button(
                onClick = {
                    val intent = Intent(context, MenuActivity::class.java)
                    context.startActivity(intent)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppColors.ButtonBackgroundHomepage
                ),
                contentPadding = PaddingValues(horizontal = 70.dp, vertical = 20.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text("PLAY", style = buttonPlay, color = AppColors.TextColor)
            }
        }
    }
}