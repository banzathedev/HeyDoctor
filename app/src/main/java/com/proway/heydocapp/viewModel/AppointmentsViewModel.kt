package com.proway.heydocapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.proway.heydocapp.model.AppointMentsTable
import com.proway.heydocapp.model.AppointmentsPojo
import com.proway.heydocapp.repositoy.AppointmentsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppointmentsViewModel @Inject constructor(private val appointmentsRepository: AppointmentsRepository) :
    ViewModel() {
    private val _addResponse = MutableLiveData<Boolean>()
    var addResponse: LiveData<Boolean> = _addResponse

    fun makeAppointment(appointment: AppointMentsTable) {
        appointmentsRepository.addAppointment(appointment) {
            _addResponse.value = it
        }
    }

    private val _appointmentsResponse = MutableLiveData<List<AppointmentsPojo>>()
    var appointmentsResponse: LiveData<List<AppointmentsPojo>> = _appointmentsResponse

    fun getAppointments() {
        appointmentsRepository.getAppointments().let {
            _appointmentsResponse.value = it
        }
    }

    private val _appointmentsByDoc = MutableLiveData<List<AppointmentsPojo>>()
    var appointmentsByDoc: LiveData<List<AppointmentsPojo>> = _appointmentsByDoc

    fun getAppointmentsByDoc(name: String) {
        appointmentsRepository.fetchAppointmentsByDoc(name).let {
            _appointmentsByDoc.value = it
        }
    }

    private val _appointmentsByPatient = MutableLiveData<List<AppointmentsPojo>>()
    var appointmentsByPatient: LiveData<List<AppointmentsPojo>> = _appointmentsByPatient

    fun getAppointmentsByPatient(name: String) {
        appointmentsRepository.fetchAppointmentsByPatient(name).let {
            _appointmentsByPatient.value = it
        }
    }

    private val _appointmentsById = MutableLiveData<AppointmentsPojo>()
    var appointmentsById: LiveData<AppointmentsPojo> = _appointmentsById

    fun getByiD(id: Int) {
        appointmentsRepository.fetchAppointmentsById(id).let {
            _appointmentsById.value = it
        }
    }

    private val _updateResponse = MutableLiveData<Boolean>()
    var updateResponse: LiveData<Boolean> = _updateResponse

    fun editAnAppointment(appointment: AppointMentsTable) {
        appointmentsRepository.update(appointment)
        _updateResponse.value = true
    }

    private val _deleteResponse = MutableLiveData<Boolean>()
    var deleteResponse: LiveData<Boolean> = _deleteResponse

    fun delete(appointment: AppointMentsTable){
        appointmentsRepository.delete(appointment)
    }


}