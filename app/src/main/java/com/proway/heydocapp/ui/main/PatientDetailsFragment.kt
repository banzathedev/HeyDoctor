package com.proway.heydocapp.ui.main

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import com.proway.heydocapp.R
import com.proway.heydocapp.databinding.PatientDetailsFragmentBinding
import com.proway.heydocapp.model.PatientsTable
import com.proway.heydocapp.model.PatientsWithDoctors
import com.proway.heydocapp.utils.replaceView
import com.proway.heydocapp.viewModel.PatientDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PatientDetailsFragment : Fragment(R.layout.patient_details_fragment) {

    companion object {
        fun newInstance() = PatientDetailsFragment()
    }

    // variables goes here
    private lateinit var viewModel: PatientDetailsViewModel
    private lateinit var binding: PatientDetailsFragmentBinding
    private var patient: PatientsWithDoctors? = null
    //obserers goes here


    override fun onAttach(context: Context) {
        super.onAttach(context)
        val _patient = arguments?.getSerializable("paciente") as PatientsWithDoctors?
        patient = _patient
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(PatientDetailsViewModel::class.java)
        viewModel.editResponse.observe(viewLifecycleOwner, observerEdit)
        viewModel.deleteResponse.observe(viewLifecycleOwner, observerDelete)
        binding = PatientDetailsFragmentBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        loadFragmentBining()
    }

    private fun loadFragmentBining() {
        binding.patientNameEditText.setText(patient?.patient?.name)
        binding.patientAgeEditText.setText(patient?.patient?.age.toString())
        binding.patientSexEditText.setText(patient?.patient?.sex)

        binding.buttomEdit.setOnClickListener {
            val name = binding.patientNameEditText.text.toString()
            val age = binding.patientAgeEditText.text.toString().toInt()
            val sex = binding.patientSexEditText.text.toString()
            patient?.patient?.id?.let {
                val model = PatientsTable(
                    id = it,
                    age = age,
                    name = name,
                    sex = sex,
                    doctorIdFk = patient?.patient?.doctorIdFk
                )
                viewModel.editPatient(model)
            }

        }

        binding.buttomDelete.setOnClickListener {
            viewModel.deletePatient(patient?.patient!!)
        }
    }

    private val observerEdit = Observer<Boolean> {
        if (it == true) {
            requireActivity().replaceView(PatientFragment())
        }
    }
    private val observerDelete = Observer<Boolean> {
        if (it == true) {
            requireActivity().replaceView(PatientFragment())
        }
    }


}