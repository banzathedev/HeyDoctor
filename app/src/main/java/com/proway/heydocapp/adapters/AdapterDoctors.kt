package com.proway.heydocapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.proway.heydocapp.R
import com.proway.heydocapp.databinding.ItemListDoctorsBinding
import com.proway.heydocapp.model.DoctorWithSpecialties


class AdapterDoctors(val OnItemClick: (DoctorWithSpecialties) -> Unit) :
    RecyclerView.Adapter<DoctorsViewHolder>() {

    private var listOfDoctors = mutableListOf<DoctorWithSpecialties>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorsViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.item_list_doctors, parent, false)
            .apply {
                return DoctorsViewHolder(this)
            }
    }

    override fun onBindViewHolder(holder: DoctorsViewHolder, position: Int) {
        listOfDoctors[position].apply {
            holder.bind(this)
            holder.itemView.setOnClickListener { OnItemClick(this) }
        }
    }

    override fun getItemCount(): Int = listOfDoctors.size
    fun update(newList: List<DoctorWithSpecialties>) {
        listOfDoctors.clear()
        listOfDoctors.addAll(newList)
        notifyDataSetChanged()
    }
}

class DoctorsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var binding = ItemListDoctorsBinding.bind(itemView)
    fun bind(doctors: DoctorWithSpecialties) {
        binding.doctorNameTextView.text = "Nome do médico: ${doctors.doctor?.name}"
        binding.doctorSpecialtiesTextView.text =
            "Especialidade do médico ${doctors.specialties?.name}"
    }
}