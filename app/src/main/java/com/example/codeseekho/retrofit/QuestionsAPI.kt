package com.example.codeseekho.retrofit

import com.example.codeseekho.model.QuestionsList
import retrofit2.Response
import retrofit2.http.GET

interface QuestionsAPI {

    @GET("questionsapi.php")
    suspend fun getQuestions(): Response<QuestionsList>
}