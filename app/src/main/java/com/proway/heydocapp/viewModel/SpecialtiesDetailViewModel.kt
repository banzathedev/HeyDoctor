package com.proway.heydocapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.proway.heydocapp.model.SpecialtiesTable
import com.proway.heydocapp.repositoy.SpecialtiesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SpecialtiesDetailViewModel @Inject constructor(private val specilatiesRepository: SpecialtiesRepository) :
    ViewModel() {
    private val _editResponse = MutableLiveData<Boolean>()
    var editResponse: LiveData<Boolean> = _editResponse

    fun editPatient(specialties: SpecialtiesTable) {
        specilatiesRepository.addSpecialties(specialties) { resp ->
            _editResponse.value = resp
        }
    }

    private val _deleteResponse = MutableLiveData<Boolean>()
    var deleteResponse: LiveData<Boolean> = _deleteResponse

    fun deletePatient(specialties: SpecialtiesTable){
        specilatiesRepository.delete(specialties) { resp ->
            _deleteResponse.value = resp
        }
    }

}