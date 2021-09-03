package com.proway.heydocapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.proway.heydocapp.model.DoctorWithSpecialties
import com.proway.heydocapp.repositoy.DocRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DoctorsDetailViewModel @Inject constructor(private val doctorsRepository: DocRepository) :
    ViewModel() {
    private val _editResponse = MutableLiveData<Boolean>()
    var editResponse: LiveData<Boolean> = _editResponse

    fun editPatient(doctor: DoctorWithSpecialties) {
        doctorsRepository.addDoctor(doctor) { resp ->
            _editResponse.value = resp
        }
    }

    private val _deleteResponse = MutableLiveData<Boolean>()
    var deleteResponse: LiveData<Boolean> = _deleteResponse

    fun deletePatient(doctor: DoctorWithSpecialties) {
        doctorsRepository.delete(doctor) { resp ->
            _deleteResponse.value = resp
        }
    }

}