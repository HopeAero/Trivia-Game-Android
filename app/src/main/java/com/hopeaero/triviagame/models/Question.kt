package com.hopeaero.triviagame.models

data class Question(
    val text: String,
    val options: List<String>,
    val correctAnswer: String,
    var selectedAnswer: String? = null
)
