package com.proway.heydocapp.viewModel

import androidx.lifecycle.ViewModel
import com.proway.heydocapp.repositoy.AppointmentsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppointmentsViewModel @Inject constructor(private val appointmentsRepository: AppointmentsRepository): ViewModel() {
    // TODO: Implement the ViewModel
}