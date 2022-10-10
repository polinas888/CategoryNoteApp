package com.example.categorynoteapp.ui.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.categorynoteapp.databinding.ItemCategoryBinding
import com.example.categorynoteapp.model.Category

class CategoryAdapter(
    private val listCategories: List<Category>,
    private val onItemClick: (Category) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCategoryBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        getCategory(position).also {
            viewHolder.bind(it)
        }

        viewHolder.itemView.setOnClickListener { onItemClick(listCategories[position]) }
    }

    override fun getItemCount() = listCategories.size

    private fun getCategory(position: Int): Category {
        return listCategories[position]
    }

    inner class ViewHolder(
        private val binding: ItemCategoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Category) {
            binding.category = category
        }
    }
}