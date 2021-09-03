package com.proway.heydocapp.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.proway.heydocapp.R
import com.proway.heydocapp.viewModel.DoctorsDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DoctorsDetailFragment : Fragment() {

    companion object {
        fun newInstance() = DoctorsDetailFragment()
    }

    private lateinit var viewModel: DoctorsDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.doctors_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DoctorsDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}