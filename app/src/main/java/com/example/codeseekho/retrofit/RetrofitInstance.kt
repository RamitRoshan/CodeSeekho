package com.example.codeseekho.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "http://10.0.2.2/quiz/api/" // Ensure the trailing slash is present

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getQuestionsAPI(): QuestionsAPI {
        return retrofit.create(QuestionsAPI::class.java)
    }
}

