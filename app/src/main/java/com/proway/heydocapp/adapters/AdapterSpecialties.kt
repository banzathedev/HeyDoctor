package com.proway.heydocapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.proway.heydocapp.R
import com.proway.heydocapp.databinding.ItemListSpecialtiesBinding
import com.proway.heydocapp.model.SpecialtiesTable

class AdapterSpecialties(val OnItemClick: (SpecialtiesTable) -> Unit) :
    RecyclerView.Adapter<SpecialtiesViewHolder>() {
    private var listOfSpecialties = mutableListOf<SpecialtiesTable>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialtiesViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.item_list_specialties, parent, false)
            .apply {
                return SpecialtiesViewHolder(this)
            }
    }

    override fun onBindViewHolder(holder: SpecialtiesViewHolder, position: Int) {
        listOfSpecialties[position].apply{
            holder.bind(this)
            holder.itemView.setOnClickListener { OnItemClick(this) }
        }
    }

    override fun getItemCount(): Int = listOfSpecialties.size
    fun update(newList: List<SpecialtiesTable>){
        listOfSpecialties.clear()
        listOfSpecialties.addAll(newList)
        notifyDataSetChanged()
    }
}
class SpecialtiesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    private var binding = ItemListSpecialtiesBinding.bind(itemView)
    fun bind(specialties: SpecialtiesTable){
        binding.specialtiesTextViewList.text = specialties.name

    }
}