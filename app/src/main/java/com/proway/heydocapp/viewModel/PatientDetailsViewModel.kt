package com.proway.heydocapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.proway.heydocapp.model.PatientsTable
import com.proway.heydocapp.repositoy.PatientsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PatientDetailsViewModel @Inject constructor(private val patientsRepository: PatientsRepository) : ViewModel() {
    private val _editResponse = MutableLiveData<Boolean>()
    var editResponse: LiveData<Boolean> = _editResponse

    fun editPatient(patient: PatientsTable) {
        patientsRepository.addPatient(patient) { resp ->
            _editResponse.value = resp
        }
    }

    private val _deleteResponse = MutableLiveData<Boolean>()
    var deleteResponse: LiveData<Boolean> = _deleteResponse

    fun deletePatient(patient: PatientsTable) {
        patientsRepository.deletePatient(patient) { resp ->
            _deleteResponse.value = resp
        }
    }

}