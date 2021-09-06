package com.proway.heydocapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.proway.heydocapp.R
import com.proway.heydocapp.databinding.ItemListAppointmentsBinding
import com.proway.heydocapp.model.AppointmentsPojo

class AdapterAppointments(val OnItemClick: (AppointmentsPojo) -> Unit) :
    RecyclerView.Adapter<AppointmentsViewHolder>() {
    private var listOfAppointments = mutableListOf<AppointmentsPojo>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentsViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.item_list_appointments, parent, false)
            .apply {
                return AppointmentsViewHolder(this)
            }
    }

    override fun onBindViewHolder(holder: AppointmentsViewHolder, position: Int) {
        listOfAppointments[position].apply {
            holder.Bind(this)
            holder.itemView.setOnClickListener { OnItemClick(this) }
        }
    }

    override fun getItemCount(): Int = listOfAppointments.size

    fun update(newList: List<AppointmentsPojo>) {
        listOfAppointments.clear()
        listOfAppointments.addAll(newList)
        notifyDataSetChanged()
    }
}

class AppointmentsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var binding = ItemListAppointmentsBinding.bind(itemView)
    fun Bind(appointments: AppointmentsPojo) {
        binding.patientNameTextView.text = "O paciente é :${appointments.patient.name}"
        binding.doctorTextView.text ="O médico é : ${appointments.doctors?.name}"
    }
}