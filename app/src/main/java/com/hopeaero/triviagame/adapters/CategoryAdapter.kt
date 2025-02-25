package com.hopeaero.triviagame.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hopeaero.triviagame.databinding.ItemCategoryBinding
import com.hopeaero.triviagame.models.Category

class CategoryAdapter(
    private val categories: List<Category>,
    private val onItemClick: (Category) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var selectedPosition: Int = -1

    inner class CategoryViewHolder(private val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Category, position: Int) {
            binding.categoryTitle.text = category.name
            binding.categoryQuizzes.text = "${category.quizCount} Preguntas"
            binding.categoryIcon.setImageResource(category.iconResId)

            // Cambia el color si está seleccionada
            binding.categoryCard.setCardBackgroundColor(
                if (position == selectedPosition) android.graphics.Color.parseColor("#87ff4d")
                else android.graphics.Color.parseColor("#FFFFFF")
            )

            // Seleccionar solo una categoría
            binding.root.setOnClickListener {
                if (selectedPosition != position) {
                    val prevSelected = selectedPosition
                    selectedPosition = position
                    notifyItemChanged(prevSelected) // Actualizar la anterior selección
                    notifyItemChanged(selectedPosition) // Actualizar la nueva selección
                    onItemClick(category)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categories[position], position)
    }

    override fun getItemCount(): Int = categories.size
}
