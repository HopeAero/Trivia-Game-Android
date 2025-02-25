package com.hopeaero.triviagame

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.hopeaero.triviagame.adapters.AnswerAdapter
import com.hopeaero.triviagame.databinding.ActivityQuizzBinding
import com.hopeaero.triviagame.models.Question
import com.hopeaero.triviagame.provider.DataProvider

class QuizzActivity : ComponentActivity() {
    private lateinit var binding: ActivityQuizzBinding
    private lateinit var answerAdapter: AnswerAdapter
    private var questions: List<Question> = emptyList()
    private var currentQuestionIndex = 0
    private var categoryName: String? = null
    private var correctAnswers = 0
    private var incorrectAnswers = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizzBinding.inflate(layoutInflater)
        setContentView(binding.root)

        categoryName = intent.getStringExtra("category_name")
        questions = DataProvider.getQuestionsForCategory(categoryName ?: "")

        showQuestion()

        binding.nextButton.setOnClickListener {
            finishQuiz()
        }
    }

    private fun showQuestion() {
        if (currentQuestionIndex < questions.size) {
            val question = questions[currentQuestionIndex]
            binding.questionProgress.text = "${currentQuestionIndex + 1} de ${questions.size}"
            binding.questionText.text = question.text

            answerAdapter = AnswerAdapter(question.options, question.correctAnswer) { selectedAnswer ->
                checkAnswer(selectedAnswer)
            }

            binding.recyclerViewAnswers.layoutManager = LinearLayoutManager(this)
            binding.recyclerViewAnswers.adapter = answerAdapter

            binding.nextButton.visibility = View.GONE
        } else {
            showFinishButton()
        }
    }

    private fun checkAnswer(selectedAnswer: String) {
        val question = questions[currentQuestionIndex]

        if (selectedAnswer == question.correctAnswer) {
            correctAnswers++
        } else {
            incorrectAnswers++
        }

        binding.recyclerViewAnswers.postDelayed({
            nextQuestion()
        }, 1000)
    }

    private fun nextQuestion() {
        if (currentQuestionIndex < questions.size - 1) {
            currentQuestionIndex++
            showQuestion()
        } else {
            showFinishButton()
        }
    }

    private fun showFinishButton() {
        binding.nextButton.text = "Finalizar Quiz"
        binding.nextButton.visibility = View.VISIBLE
    }

    private fun finishQuiz() {
        val intent = Intent(this, ResultActivity::class.java).apply {
            putExtra("correct_answers", correctAnswers)
            putExtra("incorrect_answers", incorrectAnswers)
        }
        startActivity(intent)
        finish()
    }
}
