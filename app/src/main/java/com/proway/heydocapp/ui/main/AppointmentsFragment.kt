package com.proway.heydocapp.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.proway.heydocapp.R
import com.proway.heydocapp.adapters.AdapterAppointments
import com.proway.heydocapp.adapters.PatientViewHolder
import com.proway.heydocapp.databinding.AppointmentsFragmentBinding
import com.proway.heydocapp.model.*
import com.proway.heydocapp.viewModel.AppointmentsViewModel
import com.proway.heydocapp.viewModel.DoctorsViewModel
import com.proway.heydocapp.viewModel.PatientViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AppointmentsFragment : Fragment(R.layout.appointments_fragment) {

    companion object {
        fun newInstance() = AppointmentsFragment()
    }
   /* variables goes here*/
    private lateinit var viewModel: AppointmentsViewModel
    private lateinit var viewModelDoc: DoctorsViewModel
    private lateinit var viewModelPat: PatientViewModel
    private lateinit var binding: AppointmentsFragmentBinding
    private lateinit var recycler: RecyclerView
    private  var adapter = AdapterAppointments(){

    }
    /* observers goes here*/
    private var observerGetAppointments= Observer<List<AppointmentsPojo>>{
        adapter.update(it)
    }
    private var observerDocs = Observer<List<DoctorWithSpecialties>> {
        val mutableIt = it.toMutableList()
        binding.spinnerDoctor.adapter = ArrayAdapter(
            requireContext(), android.R.layout.simple_spinner_item, mutableIt)
    }
    private var observerPat = Observer<List<PatientsWithDoctors>> {
        val mutableIt = it.toMutableList()
        binding.spinnerPatient.adapter = ArrayAdapter(
            requireContext(), android.R.layout.simple_spinner_item, mutableIt)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = AppointmentsFragmentBinding.bind(view)
        viewModel = ViewModelProvider(this).get(AppointmentsViewModel::class.java)
        viewModelDoc = ViewModelProvider(this).get(DoctorsViewModel::class.java)
        viewModelPat = ViewModelProvider(this).get(PatientViewModel::class.java)
        super.onViewCreated(view, savedInstanceState)
        /*Observers bind goses here*/
        viewModel.appointmentsResponse.observe(viewLifecycleOwner, observerGetAppointments)
        viewModelDoc.doctorsResponse.observe(viewLifecycleOwner, observerDocs)
        viewModelPat.patientsResponse.observe(viewLifecycleOwner, observerPat)

        viewModel.getAppointments()
        viewModelDoc.getDoctors()
        viewModelPat.getPatients()

        loadRecycler(view)

        binding.buttomSave.setOnClickListener {
            val selectedDoctor = binding.spinnerDoctor.selectedItem as DoctorWithSpecialties
            val selectedPat = binding.spinnerPatient.selectedItem as PatientsWithDoctors
            val model = AppointMentsTable(patientFK = selectedPat.patient!!.id, doctorFK = selectedDoctor.doctor!!.id)
            viewModel.makeAppointment(model)
            viewModel.getAppointments()
        }
    }
    private fun loadRecycler(view: View) {
        recycler = binding.recyclerViewAppointments
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter
    }


}