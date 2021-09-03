package com.proway.heydocapp.ui.main

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.proway.heydocapp.R
import com.proway.heydocapp.adapters.AdapterPatient
import com.proway.heydocapp.databinding.PatientFragmentBinding
import com.proway.heydocapp.model.PatientsTable
import com.proway.heydocapp.model.PatientsWithDoctors
import com.proway.heydocapp.utils.replaceView
import com.proway.heydocapp.viewModel.PatientViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.lang.invoke.CallSite

@AndroidEntryPoint
class PatientFragment : Fragment(R.layout.patient_fragment) {

    companion object {
        fun newInstance() = PatientFragment()
    }
        // variables goes here
    private lateinit var viewModel: PatientViewModel
    private lateinit var binding: PatientFragmentBinding
    private lateinit var recyclerView: RecyclerView
    private val adapter = AdapterPatient(){

        val args = Bundle()
        args.putSerializable("paciente", it)
        val fragment = PatientDetailsFragment.newInstance()
        fragment.arguments = args
        requireActivity().replaceView(fragment)

    }
    // observers goes here
    private var observerPatients = Observer<List<PatientsWithDoctors>>{
        adapter.update(it)
    }
    private var observerAddResponse = Observer<Boolean> {
        if (it == true){ Snackbar.make(requireView(), "Deu bom", Snackbar.LENGTH_LONG).show() }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(PatientViewModel::class.java)
        viewModel.patientsResponse.observe(viewLifecycleOwner, observerPatients)
        viewModel.getPatients()
        binding = PatientFragmentBinding.bind(view)
        loadRecycler(view)

        binding.buttomSave.setOnClickListener {
          val age =  binding.patientAgeEditText.text.toString().toInt()
           val name = binding.patientNameEditText.text.toString()
            val sex =binding.patientSexEditText.text.toString()
            val model = PatientsTable(name = name, age = age, sex = sex, doctorIdFk = null)
            viewModel.insertPatient(model)
            viewModel.getPatients()
        }
    }

    private fun loadRecycler(view: View) {
        recyclerView = binding.recyclerViewPatient
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }


}