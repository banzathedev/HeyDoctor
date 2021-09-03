package com.proway.heydocapp.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.proway.heydocapp.R
import com.proway.heydocapp.viewModel.DoctorsViewModel

class DoctorsFragment : Fragment() {

    companion object {
        fun newInstance() = DoctorsFragment()
    }

    private lateinit var viewModel: DoctorsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.doctors_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DoctorsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}