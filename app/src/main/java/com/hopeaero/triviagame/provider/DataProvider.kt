package com.hopeaero.triviagame.provider

import com.hopeaero.triviagame.R
import com.hopeaero.triviagame.models.Category
import com.hopeaero.triviagame.models.Question

object DataProvider {
    private val questionsByCategory = mapOf(
        "Historia" to listOf(
            Question("¿En qué año comenzó la Segunda Guerra Mundial?", listOf("1939", "1945", "1914", "1963"), "1939"),
            Question("¿Quién descubrió América?", listOf("Cristóbal Colón", "Simón Bolívar", "Napoleón", "Leonardo da Vinci"), "Cristóbal Colón")
        ),
        "Ciencia" to listOf(
            Question("¿Cuál es el planeta más grande del sistema solar?", listOf("Tierra", "Marte", "Júpiter", "Saturno"), "Júpiter"),
            Question("¿Qué elemento químico tiene el símbolo 'O'?", listOf("Oro", "Osmio", "Oxígeno", "Olmio"), "Oxígeno")
        ),
        "Deportes" to listOf(
            Question("¿Cuántos jugadores tiene un equipo de fútbol?", listOf("9", "10", "11", "12"), "11"),
            Question("¿En qué país se originó el baloncesto?", listOf("EE.UU.", "España", "Francia", "Brasil"), "EE.UU.")
        )
    )

    val categories = questionsByCategory.map { (name, questions) ->
        Category(name, questions.size, getCategoryIcon(name))
    }

    fun getQuestionsForCategory(categoryName: String): List<Question> {
        return questionsByCategory[categoryName] ?: emptyList()
    }

    private fun getCategoryIcon(categoryName: String): Int {
        return when (categoryName) {
            "Historia" -> R.drawable.history_book
            "Ciencia" -> R.drawable.science
            "Deportes" -> R.drawable.football
            else -> R.drawable.baseline_videogame_asset_24
        }
    }
}
