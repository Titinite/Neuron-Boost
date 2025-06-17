package com.supdevinci.neuronboost.model

data class QuizzResponse(
    val response_code: Int,
    val results: List<QuizzQuestion>
)
