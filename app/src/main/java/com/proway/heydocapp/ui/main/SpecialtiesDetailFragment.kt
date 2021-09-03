package com.proway.heydocapp.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.proway.heydocapp.R
import com.proway.heydocapp.viewModel.SpecialtiesDetailViewModel

class SpecialtiesDetailFragment : Fragment() {

    companion object {
        fun newInstance() = SpecialtiesDetailFragment()
    }

    private lateinit var viewModel: SpecialtiesDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.specialties_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SpecialtiesDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}