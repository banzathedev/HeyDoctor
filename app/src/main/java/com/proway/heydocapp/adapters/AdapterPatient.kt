package com.proway.heydocapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.proway.heydocapp.R
import com.proway.heydocapp.databinding.ItemListPatientBinding
import com.proway.heydocapp.model.PatientsWithDoctors

class AdapterPatient(val OnItemClick: (PatientsWithDoctors) -> Unit) :
    RecyclerView.Adapter<PatientViewHolder>() {
    private var listOfPatients = mutableListOf<PatientsWithDoctors>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.item_list_patient, parent, false)
            .apply {
                return PatientViewHolder(this)
            }
    }

    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        listOfPatients[position].apply {
            holder.bind(this)
            holder.itemView.setOnClickListener { OnItemClick(this) }
        }
    }

    override fun getItemCount(): Int = listOfPatients.size

    fun update(newlist: List<PatientsWithDoctors>) {
        listOfPatients.clear()
        listOfPatients.addAll(newlist)
        notifyDataSetChanged()
    }
}

class PatientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var binding = ItemListPatientBinding.bind(itemView)

    fun bind(patient: PatientsWithDoctors) {
        binding.PatientNameTextView.text = "Nome: ${patient.patient?.name}"
        binding.patientAgeTextView.text = "Idade: ${patient.patient?.age}"
    }
}