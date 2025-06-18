package com.supdevinci.neuronboost.components.quizz

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.supdevinci.neuronboost.ui.theme.AppColors
import com.supdevinci.neuronboost.ui.theme.buttonMenu
import com.supdevinci.neuronboost.ui.theme.labelScore
import com.supdevinci.neuronboost.ui.theme.mainFont
import com.supdevinci.neuronboost.ui.theme.titleScore
import com.supdevinci.neuronboost.view.MenuActivity
import com.supdevinci.neuronboost.view.RankingActivity
import com.supdevinci.neuronboost.viewModel.QuizzViewModel
import com.supdevinci.neuronboost.viewModel.RankingViewModel
import com.supdevinci.neuronboost.viewModel.ScoreViewModel
import com.supdevinci.neuronboost.viewModel.UserViewModel
import androidx.compose.runtime.getValue

@Composable
fun ScoreScreen(
    quizzViewModel: QuizzViewModel,
    scoreViewModel: ScoreViewModel,
    userViewModel: UserViewModel,
    rankingViewModel: RankingViewModel
) {

    val score = quizzViewModel.score.collectAsState().value
    val username by userViewModel.username.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        rankingViewModel.insertRanking(username, score)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.Background)
            .padding(horizontal = 32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp),
        ) {
            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "SCORE",
                style = titleScore,
                color = AppColors.TextColor
            )

            Box(
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(100.dp))
                    .background(scoreViewModel.getScoreColor(score))
                    .border(2.dp, color = AppColors.TextColor, shape = RoundedCornerShape(100.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "$score", style = labelScore, color = AppColors.TextColor)
            }
            Text(scoreViewModel.getScoreLabel(score), style = mainFont, color = AppColors.TextColor)

            Spacer(modifier = Modifier.height(50.dp))

            Button(
                onClick = {
                    val intent = Intent(context, MenuActivity::class.java)
                    context.startActivity(intent)
                },
                colors = ButtonDefaults.buttonColors(AppColors.ButtonModeClassic),
                shape = RoundedCornerShape(16.dp),
                contentPadding = PaddingValues(vertical = 20.dp),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text("Home", style = buttonMenu, color = AppColors.TextColor)
            }

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
        }
    }
}
