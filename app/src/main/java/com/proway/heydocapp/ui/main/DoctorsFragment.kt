package com.proway.heydocapp.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment

import android.view.View
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import androidx.recyclerview.widget.RecyclerView
import com.proway.heydocapp.R
import com.proway.heydocapp.adapters.AdapterDoctors

import com.proway.heydocapp.databinding.DoctorsFragmentBinding
import com.proway.heydocapp.model.DoctorWithSpecialties
import com.proway.heydocapp.model.DoctorsTable
import com.proway.heydocapp.model.SpecialtiesTable
import com.proway.heydocapp.utils.replaceView
import com.proway.heydocapp.viewModel.DoctorsViewModel
import com.proway.heydocapp.viewModel.SpecialtiesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DoctorsFragment : Fragment(R.layout.doctors_fragment) {

    companion object {
        fun newInstance() = DoctorsFragment()
    }

    private lateinit var viewModel: DoctorsViewModel
    private lateinit var viewModelSpecialties: SpecialtiesViewModel
    private lateinit var binding: DoctorsFragmentBinding
    private lateinit var recyclerView: RecyclerView
    private var listOfSpecialties = mutableListOf<SpecialtiesTable>()
    private val adapter = AdapterDoctors() {

        val args = Bundle()
        args.putSerializable("doctor", it)
        val fragment = DoctorsDetailFragment.newInstance()
        fragment.arguments = args
        requireActivity().replaceView(fragment)

    }
    private val observerSpecialties = Observer<List<SpecialtiesTable>> {
        val mutableIt = it.toMutableList()
        listOfSpecialties = mutableIt
        binding.spinnerSpecialties.adapter = ArrayAdapter(
            requireContext(), android.R.layout.simple_spinner_item, listOfSpecialties
        )
    }
    private val observerDoctors = Observer<List<DoctorWithSpecialties>> {
        adapter.update(it)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = DoctorsFragmentBinding.bind(view)
        viewModel = ViewModelProvider(this).get(DoctorsViewModel::class.java)
        viewModelSpecialties = ViewModelProvider(this).get(SpecialtiesViewModel::class.java)
        viewModelSpecialties.specialtiesResponse.observe(viewLifecycleOwner, observerSpecialties)
        viewModelSpecialties.getAllSpecialties()
        viewModel.getDoctors()
        viewModel.doctorsResponse.observe(viewLifecycleOwner, observerDoctors)
        super.onViewCreated(view, savedInstanceState)
        loadRecycler(view)


        binding.spinnerSpecialties.onItemSelectedListener

        binding.buttomSave.setOnClickListener {
            val name = binding.doctorNameEditText.text.toString()
            val selectedSpecialties = binding.spinnerSpecialties.selectedItem as SpecialtiesTable
            val docModel = DoctorsTable(name = name, specialtiesTableFK = selectedSpecialties.id)
            val model = DoctorWithSpecialties(docModel, selectedSpecialties)
            viewModel.insertDoctor(model)
            viewModel.getDoctors()
        }
    }

    private fun loadRecycler(view: View) {
        recyclerView = binding.recyclerViewDoctors
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }

}