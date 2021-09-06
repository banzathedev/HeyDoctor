package com.proway.heydocapp.ui.main

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment

import android.view.View
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer

import com.proway.heydocapp.R
import com.proway.heydocapp.databinding.AppointmentsDetailFragmentBinding
import com.proway.heydocapp.model.AppointMentsTable
import com.proway.heydocapp.model.AppointmentsPojo
import com.proway.heydocapp.model.DoctorWithSpecialties
import com.proway.heydocapp.model.PatientsWithDoctors
import com.proway.heydocapp.utils.replaceView


import com.proway.heydocapp.viewModel.AppointmentsViewModel
import com.proway.heydocapp.viewModel.DoctorsViewModel
import com.proway.heydocapp.viewModel.PatientViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AppointmentsDetailFragment : Fragment(R.layout.appointments_detail_fragment) {

    companion object {
        fun newInstance() = AppointmentsDetailFragment()
    }

    private lateinit var viewModel: AppointmentsViewModel
    private var pojo: AppointmentsPojo? = null
    private lateinit var binding: AppointmentsDetailFragmentBinding
    private lateinit var viewModelDoc: DoctorsViewModel
    private lateinit var viewModelPat: PatientViewModel

    private var observerDocs = Observer<List<DoctorWithSpecialties>> {
        val mutableIt = it.toMutableList()
        binding.spinnerDoctor.adapter = ArrayAdapter(
            requireContext(), android.R.layout.simple_spinner_item, mutableIt
        )
    }
    private var observerPat = Observer<List<PatientsWithDoctors>> {
        val mutableIt = it.toMutableList()
        binding.spinnerPatient.adapter = ArrayAdapter(
            requireContext(), android.R.layout.simple_spinner_item, mutableIt
        )

    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        val _pojo = arguments?.getSerializable("pojo") as AppointmentsPojo?
        pojo = _pojo
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = AppointmentsDetailFragmentBinding.bind(view)
        viewModel = ViewModelProvider(this).get(AppointmentsViewModel::class.java)
        viewModelDoc = ViewModelProvider(this).get(DoctorsViewModel::class.java)
        viewModelPat = ViewModelProvider(this).get(PatientViewModel::class.java)
        super.onViewCreated(view, savedInstanceState)
        viewModelDoc.doctorsResponse.observe(viewLifecycleOwner, observerDocs)
        viewModelPat.patientsResponse.observe(viewLifecycleOwner, observerPat)
        viewModelDoc.getDoctors()
        viewModelPat.getPatients()

        binding.buttomEdit.setOnClickListener {
            val selectedDoctor = binding.spinnerDoctor.selectedItem as DoctorWithSpecialties
            val selectedPat = binding.spinnerPatient.selectedItem as PatientsWithDoctors
            val model = AppointMentsTable(
                id = pojo!!.appointments!!.id,
                patientFK = selectedPat.patient!!.id,
                doctorFK = selectedDoctor.doctor!!.id
            )
            viewModel.editAnAppointment(model)
            viewModel.getAppointments()
            requireActivity().replaceView(AppointmentsFragment())
        }
        binding.buttomDelete.setOnClickListener {
            viewModel.delete(pojo!!.appointments!!)
            viewModel.getAppointments()
            requireActivity().replaceView(AppointmentsFragment())

        }
    }
}

