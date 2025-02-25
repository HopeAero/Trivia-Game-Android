package com.hopeaero.triviagame.adapters

import android.view.LayoutInflater
import android.graphics.Color
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hopeaero.triviagame.databinding.ItemAnswerBinding

class AnswerAdapter(
    private val options: List<String>,
    private val correctAnswer: String,
    private val onAnswerSelected: (String) -> Unit
) : RecyclerView.Adapter<AnswerAdapter.AnswerViewHolder>() {

    private var selectedAnswer: String? = null
    private var isAnswered = false

    inner class AnswerViewHolder(private val binding: ItemAnswerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(answer: String) {
            binding.answerText.text = answer

            when {
                isAnswered && answer == correctAnswer -> {
                    // Respuesta correcta → Verde
                    binding.questionCardLayout.setBackgroundColor(Color.parseColor("#4CAF50"))
                }
                isAnswered && answer == selectedAnswer -> {
                    // Respuesta incorrecta → Rojo
                    binding.questionCardLayout.setBackgroundColor(Color.parseColor("#F44336"))
                }
            }

            binding.root.setOnClickListener {
                if (!isAnswered) {
                    selectedAnswer = answer
                    isAnswered = true
                    notifyDataSetChanged()
                    onAnswerSelected(answer)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerViewHolder {
        val binding = ItemAnswerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnswerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnswerViewHolder, position: Int) {
        holder.bind(options[position])
    }

    override fun getItemCount(): Int = options.size
}
