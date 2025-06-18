package com.supdevinci.neuronboost.service

import com.supdevinci.neuronboost.model.QuizzResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Quizz {

    @GET("api.php")
    suspend fun getQuestions(
        @Query("amount") amount: Int = 20
    ): Response<QuizzResponse>
}