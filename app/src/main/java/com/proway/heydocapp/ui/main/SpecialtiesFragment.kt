package com.proway.heydocapp.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.proway.heydocapp.R
import com.proway.heydocapp.adapters.AdapterSpecialties
import com.proway.heydocapp.databinding.SpecialtiesFragmentBinding
import com.proway.heydocapp.model.SpecialtiesTable
import com.proway.heydocapp.utils.replaceView
import com.proway.heydocapp.viewModel.SpecialtiesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SpecialtiesFragment : Fragment(R.layout.specialties_fragment) {

    companion object {
        fun newInstance() = SpecialtiesFragment()
    }

    // variables goes here
    private lateinit var viewModel: SpecialtiesViewModel
    private lateinit var binding: SpecialtiesFragmentBinding
    private lateinit var recyclerView: RecyclerView
    private val adapter = AdapterSpecialties() {

        val args = Bundle()
        args.putSerializable("especialidade", it)
        val fragment = SpecialtiesDetailFragment.newInstance()
        fragment.arguments = args
        requireActivity().replaceView(fragment)

    }

    private val observerSpecialtiesResponse = Observer<List<SpecialtiesTable>> {
        adapter.update(it)
    }
    private val observerAddResp = Observer<Boolean> {
        if (it == true) {
            Snackbar.make(
                requireView(),
                "Especialidade Adicionada com sucesso",
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(SpecialtiesViewModel::class.java)
        binding = SpecialtiesFragmentBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        viewModel.specialtiesResponse.observe(viewLifecycleOwner, observerSpecialtiesResponse)
        viewModel.addResponse.observe(viewLifecycleOwner, observerAddResp)
        viewModel.getAllSpecialties()

        loadRecycler(view)
        loadBinding()
    }

    private fun loadBinding() {
        binding.buttomSave.setOnClickListener {
            val name = binding.specialtiesNameEditText.text.toString()
            val model = SpecialtiesTable(name = name)
            viewModel.addSpecialties(model)
            viewModel.getAllSpecialties()
        }
    }

    private fun loadRecycler(view: View) {
        recyclerView = binding.recyclerViewSpecialties
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }


}