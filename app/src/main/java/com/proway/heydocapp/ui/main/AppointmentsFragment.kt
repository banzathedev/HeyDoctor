package com.proway.heydocapp.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.proway.heydocapp.R
import com.proway.heydocapp.viewModel.AppointmentsViewModel

class AppointmentsFragment : Fragment(R.layout.appointments_fragment) {

    companion object {
        fun newInstance() = AppointmentsFragment()
    }

    private lateinit var viewModel: AppointmentsViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(AppointmentsViewModel::class.java)
        super.onViewCreated(view, savedInstanceState)

    }

}