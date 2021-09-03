package com.proway.heydocapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.proway.heydocapp.model.DoctorWithSpecialties

import com.proway.heydocapp.repositoy.DocRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DoctorsViewModel @Inject constructor(private val doctorsRepository: DocRepository) : ViewModel() {

    private val _DoctorsResponse = MutableLiveData<List<DoctorWithSpecialties>>()
    var doctorsResponse: LiveData<List<DoctorWithSpecialties>> = _DoctorsResponse

    fun getDoctors() {
        doctorsRepository.getDoctors() { foundDoctors, e ->
            _DoctorsResponse.value = foundDoctors
        }
    }

    private val _insertResponse = MutableLiveData<Boolean>()
    var insertResponse: LiveData<Boolean> = _insertResponse

    fun insertDoctor(doctors: DoctorWithSpecialties) {
        doctorsRepository.addDoctor(doctors) { resp ->
            _insertResponse.value = resp
        }
    }
}