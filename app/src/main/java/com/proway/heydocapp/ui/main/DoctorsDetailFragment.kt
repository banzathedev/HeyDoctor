package com.proway.heydocapp.ui.main

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import com.proway.heydocapp.R
import com.proway.heydocapp.databinding.DoctorsDetailFragmentBinding
import com.proway.heydocapp.model.DoctorWithSpecialties
import com.proway.heydocapp.model.DoctorsTable
import com.proway.heydocapp.model.PatientsWithDoctors
import com.proway.heydocapp.model.SpecialtiesTable
import com.proway.heydocapp.utils.replaceView
import com.proway.heydocapp.viewModel.DoctorsDetailViewModel
import com.proway.heydocapp.viewModel.SpecialtiesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DoctorsDetailFragment : Fragment(R.layout.doctors_detail_fragment) {

    companion object {
        fun newInstance() = DoctorsDetailFragment()
    }

    private lateinit var viewModel: DoctorsDetailViewModel
    private lateinit var binding: DoctorsDetailFragmentBinding
    private lateinit var viewModelSpecialties: SpecialtiesViewModel

    private var doc: DoctorWithSpecialties? = null
    private var listOfSpecialties = mutableListOf<SpecialtiesTable>()

    private val observerSpecialties = Observer<List<SpecialtiesTable>> {
        val mutableIt = it.toMutableList()
        listOfSpecialties = mutableIt
        binding.spinnerSpecialties.adapter = ArrayAdapter(
            requireContext(), android.R.layout.simple_spinner_item, listOfSpecialties
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val _doc = arguments?.getSerializable("doctor") as DoctorWithSpecialties?
        doc = _doc
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = DoctorsDetailFragmentBinding.bind(view)
        viewModel = ViewModelProvider(this).get(DoctorsDetailViewModel::class.java)
        viewModelSpecialties = ViewModelProvider(this).get(SpecialtiesViewModel::class.java)
        viewModelSpecialties.specialtiesResponse.observe(viewLifecycleOwner, observerSpecialties)
        viewModelSpecialties.getAllSpecialties()
        super.onViewCreated(view, savedInstanceState)

        binding.buttomEdit.setOnClickListener {
            val name = binding.doctorNameEditText.text.toString()
            val selectedSpecialties = binding.spinnerSpecialties.selectedItem as SpecialtiesTable
            val docModel = DoctorsTable(name = name, specialtiesTableFK = selectedSpecialties.id)
            val model = DoctorWithSpecialties(docModel, selectedSpecialties)
            viewModel.editDoctor(model)
            requireActivity().replaceView(DoctorsFragment())
        }
        binding.buttomDelete.setOnClickListener {
            viewModel.deleteDoctor(doc?.doctor!!)
            requireActivity().replaceView(DoctorsFragment())
        }
    }
}

