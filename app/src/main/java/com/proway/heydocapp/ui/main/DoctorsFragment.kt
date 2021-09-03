package com.proway.heydocapp.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment

import android.view.View

import androidx.recyclerview.widget.RecyclerView
import com.proway.heydocapp.R
import com.proway.heydocapp.adapters.AdapterDoctors

import com.proway.heydocapp.databinding.DoctorsFragmentBinding
import com.proway.heydocapp.utils.replaceView
import com.proway.heydocapp.viewModel.DoctorsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DoctorsFragment : Fragment(R.layout.doctors_fragment) {

    companion object {
        fun newInstance() = DoctorsFragment()
    }

    private lateinit var viewModel: DoctorsViewModel
    private lateinit var binding: DoctorsFragmentBinding
    private lateinit var recyclerView: RecyclerView
    private val adapter = AdapterDoctors(){

        val args = Bundle()
        args.putSerializable("paciente", it)
        val fragment = PatientDetailsFragment.newInstance()
        fragment.arguments = args
        requireActivity().replaceView(fragment)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = DoctorsFragmentBinding.bind(view)
        viewModel = ViewModelProvider(this).get(DoctorsViewModel::class.java)
        super.onViewCreated(view, savedInstanceState)
    }

}