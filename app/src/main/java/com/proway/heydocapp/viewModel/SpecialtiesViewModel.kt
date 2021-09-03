package com.proway.heydocapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.proway.heydocapp.model.SpecialtiesTable
import com.proway.heydocapp.repositoy.SpecialtiesRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SpecialtiesViewModel @Inject constructor(private val specialtiesRepository: SpecialtiesRepository) :
    ViewModel() {
    private val _addResponse = MutableLiveData<Boolean>()
    var addResponse: LiveData<Boolean> = _addResponse

    fun addSpecialties(specialties: SpecialtiesTable) {
        specialtiesRepository.addSpecialties(specialties) {

        }
    }

    private val _specialtiesResponse = MutableLiveData<List<SpecialtiesTable>>()
    var specialtiesResponse: LiveData<List<SpecialtiesTable>> = _specialtiesResponse

    fun getAllSpecialties() {
        specialtiesRepository.getSpecialties() { foundSpecialties, e ->
            _specialtiesResponse.value = foundSpecialties
        }
    }
}