package com.example.codeseekho.retrofit

import com.example.codeseekho.model.Question
import retrofit2.http.GET

interface QuestionsAPI {
    @GET("fetch_questions.php")  // Adjust the endpoint according to your server setup
    suspend fun getQuestions(): List<Question>
}
