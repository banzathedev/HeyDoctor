package com.proway.heydocapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.AdapterView
import com.google.android.material.navigation.NavigationBarView
import com.proway.heydocapp.R
import com.proway.heydocapp.databinding.MainActivityBinding
import com.proway.heydocapp.utils.replaceView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = MainActivityBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PatientFragment.newInstance())
                .commitNow()
        }
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            if (item.itemId == R.id.buttomPatient) {
                replaceView(PatientFragment())
            }
            if (item.itemId == R.id.buttomDoctors) {
                replaceView(DoctorsFragment())
            }
            if (item.itemId == R.id.buttomAppointments) {
                replaceView(AppointmentsFragment())
            }
            if (item.itemId == R.id.buttomSpecialties) {
                replaceView(SpecialtiesFragment())
            }
            return@setOnItemSelectedListener true


        }


    }


}