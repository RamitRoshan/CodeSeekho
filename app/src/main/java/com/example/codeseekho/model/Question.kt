package com.example.codeseekho.model

import com.google.gson.annotations.SerializedName

data class Question(
    @SerializedName("question") val question: String,
    @SerializedName("option1") val option1: String,
    @SerializedName("option2") val option2: String,
    @SerializedName("option3") val option3: String,
    @SerializedName("option4") val option4: String,
    @SerializedName("correct_option") val correctOption: String // This will be the correct answer string
) {
    // Return the options in a list
    fun getOptions(): List<String> = listOf(option1, option2, option3, option4)
}
