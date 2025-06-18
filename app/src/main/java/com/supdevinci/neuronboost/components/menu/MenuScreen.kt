package com.supdevinci.neuronboost.components.menu

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import com.supdevinci.neuronboost.ui.theme.AppColors
import com.supdevinci.neuronboost.R
import com.supdevinci.neuronboost.ui.theme.buttonMenu
import com.supdevinci.neuronboost.ui.theme.mainFont
import com.supdevinci.neuronboost.view.MenuActivity
import com.supdevinci.neuronboost.view.QuizzActivity
import com.supdevinci.neuronboost.view.RankingActivity
import com.supdevinci.neuronboost.viewModel.UserViewModel

@Composable
fun MenuScreen(userViewModel: UserViewModel) {
    val context = LocalContext.current
    var username by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.Background)
            .padding(horizontal = 32.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 40.dp, bottom = 32.dp)
        ) {

            Spacer(modifier = Modifier.weight(1f))

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(200.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    val intent = Intent(context, QuizzActivity::class.java)
                    intent.putExtra("username", username)
                    context.startActivity(intent)
                },
                colors = ButtonDefaults.buttonColors(AppColors.ButtonModeClassic),
                shape = RoundedCornerShape(16.dp),
                contentPadding = PaddingValues(vertical = 20.dp),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text("Classic", style = buttonMenu, color = AppColors.TextColor)
            }

            Button(
                onClick = {  },
                colors = ButtonDefaults.buttonColors(AppColors.ButtonModeStuddendeath),
                shape = RoundedCornerShape(16.dp),
                contentPadding = PaddingValues(vertical = 20.dp),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text("Sudden death", style = buttonMenu, color = AppColors.TextColor)
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    val intent = Intent(context, RankingActivity::class.java)
                    context.startActivity(intent)
                },
                colors = ButtonDefaults.buttonColors(AppColors.ButtonRanking),
                shape = RoundedCornerShape(16.dp),
                contentPadding = PaddingValues(vertical = 20.dp),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text("Ranking", style = buttonMenu, color = AppColors.TextColor)
            }

            Spacer(modifier = Modifier.weight(1f))

            Text("My username :", style = mainFont, color = AppColors.TextColor)

            OutlinedTextField(
                value = username,
                onValueChange = {
                    username = it
                    userViewModel.setUsername(it)
                },
                singleLine = true,
                textStyle = mainFont.copy(),
                modifier = Modifier.fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(AppColors.TextColor)
                    .padding(vertical = 10.dp, horizontal = 16.dp),
                placeholder = { Text("Titinite", style = mainFont, color = AppColors.TextPlaceholderUsername) },
            )

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}
