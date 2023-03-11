package com.sardordev.translator.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sardordev.translator.R
import com.sardordev.translator.databinding.FragmentSavedBinding
import com.sardordev.translator.screens.adapter.SavedAdapter
import com.sardordev.translator.viewmodel.SavedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedFragment : Fragment() {
    private val binding by lazy { FragmentSavedBinding.inflate(layoutInflater) }
    private val savedAdapter = SavedAdapter()
    private val viewModel by viewModels<SavedViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel.getSavedWords.observe(viewLifecycleOwner, Observer {
            savedAdapter.submitList(it)
        })


        initRecycler()



        deleteAllItems()





        return binding.root
    }

    private fun initRecycler() {
        binding.rvsaved.apply {
            adapter = savedAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun deleteAllItems() {
        binding.btndelete.setOnClickListener {
            viewModel.deleteAllItems()
        }
    }


}