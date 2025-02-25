package com.hopeaero.triviagame

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.hopeaero.triviagame.adapters.CategoryAdapter
import com.hopeaero.triviagame.databinding.ActivityCategoryBinding
import com.hopeaero.triviagame.models.Category
import com.hopeaero.triviagame.provider.DataProvider

class CategoryActivity : ComponentActivity() {
    private lateinit var binding: ActivityCategoryBinding
    private lateinit var categoryAdapter: CategoryAdapter
    private var selectedCategory: Category? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        binding.nextButton.setOnClickListener {
            selectedCategory?.let {
                val intent = Intent(this, QuizzActivity::class.java).apply {
                    putExtra("category_name", it.name)
                }
                startActivity(intent)
            } ?: run {
                println("Selecciona una categoría")
            }
        }
    }

    private fun setupRecyclerView() {
        categoryAdapter = CategoryAdapter(DataProvider.categories) { selected ->
            selectedCategory = selected
            categoryAdapter.notifyDataSetChanged()
        }

        binding.recyclerViewCategories.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerViewCategories.adapter = categoryAdapter
    }
}

