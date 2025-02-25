package com.hopeaero.triviagame.models

data class Category(
    val name: String,
    val quizCount: Int,
    val iconResId: Int,
    var isSelected: Boolean = false
)