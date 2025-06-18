package com.supdevinci.neuronboost.components.ranking

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.supdevinci.neuronboost.R
import com.supdevinci.neuronboost.model.RankingEntity
import com.supdevinci.neuronboost.ui.theme.AppColors
import com.supdevinci.neuronboost.ui.theme.mainFont
import com.supdevinci.neuronboost.ui.theme.titleScore
import com.supdevinci.neuronboost.viewModel.RankingViewModel
import com.supdevinci.neuronboost.viewModel.UserViewModel

@Composable
fun RankingScreen(rankingViewModel: RankingViewModel) {
    val rankingList by rankingViewModel.rankings.collectAsState(initial = emptyList())

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.Background)
            .padding(horizontal = 32.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.weight(1f))

            Image(
                painter = painterResource(id = R.drawable.ranking),
                contentDescription = "Ranking",
                modifier = Modifier.size(150.dp)
            )

            Text(
                text = "RANKING",
                style = titleScore,
                color = AppColors.TextColor
            )

            Spacer(modifier = Modifier.height(30.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
            ) {
                items(rankingList.sortedByDescending { it.score }) { rank ->
                    RankingItem(rank)
                }
            }

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}