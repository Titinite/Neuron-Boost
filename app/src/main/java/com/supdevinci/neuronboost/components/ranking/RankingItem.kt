package com.supdevinci.neuronboost.components.ranking

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.supdevinci.neuronboost.model.RankingEntity
import com.supdevinci.neuronboost.ui.theme.AppColors
import com.supdevinci.neuronboost.ui.theme.mainFont
import com.supdevinci.neuronboost.viewModel.UserViewModel

@Composable
fun RankingItem(rank: RankingEntity) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(AppColors.RankingCase)
            .padding(vertical = 14.dp, horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = rank.username, style = mainFont, color = AppColors.TextColor)
        Text(text = rank.score.toString(), style = mainFont, color = AppColors.TextColor)
    }
}