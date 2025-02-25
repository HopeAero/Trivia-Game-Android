package com.hopeaero.triviagame

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.hopeaero.triviagame.databinding.ActivityResultBinding

class ResultActivity : ComponentActivity() {
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener los datos pasados desde QuizzActivity
        val correctAnswers = intent.getIntExtra("correct_answers", 0)
        val incorrectAnswers = intent.getIntExtra("incorrect_answers", 0)

        // Mostrar los resultados en los TextViews correspondientes
        binding.correctAnswersText.text = "Respuestas correctas: $correctAnswers"
        binding.incorrectAnswersText.text = "Respuestas incorrectas: $incorrectAnswers"

        // Botón para volver a la actividad de categorías
        binding.backToCategoriesButton.setOnClickListener {
            val intent = Intent(this, CategoryActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }
}