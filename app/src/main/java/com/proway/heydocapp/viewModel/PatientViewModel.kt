package com.proway.heydocapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.proway.heydocapp.model.PatientsTable
import com.proway.heydocapp.model.PatientsWithDoctors
import com.proway.heydocapp.repositoy.PatientsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PatientViewModel @Inject constructor(private val patientsRepository: PatientsRepository) :
    ViewModel() {

    private val _patientsResponse = MutableLiveData<List<PatientsWithDoctors>>()
    var patientsResponse: LiveData<List<PatientsWithDoctors>> = _patientsResponse

    fun getPatients() {
        patientsRepository.getPatients() { foundPatients, e ->
            _patientsResponse.value = foundPatients
        }
    }

    private val _insertResponse = MutableLiveData<Boolean>()
    var insertResponse: LiveData<Boolean> = _insertResponse

    fun insertPatient(patient: PatientsTable) {
        patientsRepository.addPatient(patient) { resp ->
            _insertResponse.value = resp
        }
    }



}