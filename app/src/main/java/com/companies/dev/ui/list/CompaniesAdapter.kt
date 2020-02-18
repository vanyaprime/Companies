package com.companies.dev.ui.list

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.companies.dev.R
import com.companies.dev.domain.entity.Company
import com.facebook.drawee.view.SimpleDraweeView

/**
 * @author Ivan Prokopyev
 */
class CompaniesAdapter(
    private val onItemCLickListener: (Company) -> Unit
) : RecyclerView.Adapter<CompaniesAdapter.CompaniesViewHolder>() {

    private val data = mutableListOf<Company>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompaniesViewHolder =
        CompaniesViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.compiny_item,
                parent,
                false
            )
        )

    fun setItems(items: List<Company>) {
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: CompaniesViewHolder, position: Int) {
        holder.bind(data[position], onItemCLickListener)
    }

    inner class CompaniesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Company, onItemCLickListener: (Company) -> Unit) {
            itemView.setOnClickListener { onItemCLickListener(item) }
            (itemView.findViewById(R.id.itemName) as TextView).text = item.name
            (itemView.findViewById(R.id.itemImage) as SimpleDraweeView).setImageURI(item.imgUrl)
        }
    }
}