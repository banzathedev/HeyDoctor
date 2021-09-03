package com.proway.heydocapp.ui.main

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.proway.heydocapp.R
import com.proway.heydocapp.databinding.SpecialtiesDetailFragmentBinding
import com.proway.heydocapp.model.PatientsWithDoctors
import com.proway.heydocapp.model.SpecialtiesTable
import com.proway.heydocapp.utils.replaceView
import com.proway.heydocapp.viewModel.SpecialtiesDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SpecialtiesDetailFragment : Fragment(R.layout.specialties_detail_fragment) {

    companion object {
        fun newInstance() = SpecialtiesDetailFragment()
    }

    private lateinit var viewModel: SpecialtiesDetailViewModel
    private lateinit var binding: SpecialtiesDetailFragmentBinding
    private var specialties: SpecialtiesTable? = null

    private var observerEdit = Observer<Boolean> {
        requireActivity().replaceView(SpecialtiesFragment())
    }
    private var observerDelete = Observer<Boolean> {
        requireActivity().replaceView(SpecialtiesFragment())
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val _specialties = arguments?.getSerializable("especialidade") as SpecialtiesTable?
        specialties = _specialties
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(SpecialtiesDetailViewModel::class.java)
        viewModel.editResponse.observe(viewLifecycleOwner, observerEdit)
        viewModel.deleteResponse.observe(viewLifecycleOwner, observerDelete)
        binding = SpecialtiesDetailFragmentBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        loadFragmentBining()
    }


    private fun loadFragmentBining() {
        binding.specialtiesNameEditText.setText(specialties?.name)

        specialties?.id?.let { idFound ->
            binding.buttomEdit.setOnClickListener {
                val name = binding.specialtiesNameEditText.text.toString()
                val model = SpecialtiesTable(idFound, name)
                viewModel.editSpecialties(model)
            }
        }
        binding.buttomDelete.setOnClickListener {
            viewModel.deleteSpecialties(specialties!!)
        }
    }
}