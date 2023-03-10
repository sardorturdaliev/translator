package com.sardordev.translator.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.sardordev.translator.R
import com.sardordev.translator.data.model.BodyTranslate
import com.sardordev.translator.data.model.ResultTranslated
import com.sardordev.translator.databinding.FragmentTranslateBinding
import com.sardordev.translator.utils.UiEvent
import com.sardordev.translator.viewmodel.TranslateWordViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class TranslateFragment : Fragment() {
    private val binding by lazy { FragmentTranslateBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<TranslateWordViewModel>()
    private var dan = "ru"
    private var gacha = "uz"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        translateWord()
        translatedWordObserver()

        changeLanguage()
        iconfunctions()

        return binding.root
    }

    private fun translateWord() {


        binding.btntranslate.setOnClickListener {
            var result = "ru"
            if (binding.tvrussian.text.contains("Russian")) result = "ru"
            if (binding.tvuzb.text.contains("Uzbek")) result = "uz"
//
            if (binding.edSentences.text.isNotEmpty()) {
                viewModel.transaleteWord(
                    BodyTranslate(
                        "$dan", arrayListOf(binding.edSentences.text.toString()),
                        arrayListOf("$gacha")
                    )
                )
            } else {
                Toast.makeText(requireContext(), "Please Enter", Toast.LENGTH_SHORT).show()
            }
        }


    }


    private fun translatedWordObserver() {
        lifecycleScope.launchWhenCreated {
            viewModel.wordobserver.collectLatest {
                when (it) {
                    UiEvent.Empty -> Unit
                    is UiEvent.Error -> {
                        Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                    }
                    UiEvent.Loading -> {
                        binding.progressbar.isVisible = true
                    }
                    is UiEvent.Success<*> -> {
                        binding.progressbar.isVisible = false
                        val itlist = it.data as List<ResultTranslated>
                        binding.tvResultTranslate.text = itlist[0].texts
                    }
                }
            }
        }
    }


    private fun changeLanguage() {
        var ischange = false
        binding.imgchange.setOnClickListener {
            if (ischange) {
                binding.imguzb.setImageResource(R.drawable.uzb)
                binding.tvuzb.setText("Uzbek")

                binding.imgrussian.setImageResource(R.drawable.rus)
                binding.tvrussian.setText("Russian")

                dan = "ru"
                gacha = "uz"
                ischange = false
            } else {
                binding.imguzb.setImageResource(R.drawable.rus)
                binding.tvuzb.setText("Russian")

                binding.imgrussian.setImageResource(R.drawable.uzb)
                binding.tvrussian.setText("Uzbek")

                dan = "uz"
                gacha = "ru"
                ischange = true
            }

        }
    }

    private fun iconfunctions() {
        binding.imgtrash.setOnClickListener {
            binding.edSentences.text.clear()
            binding.tvResultTranslate.text = ""
        }

        binding.imgcopy.setOnClickListener {
            val clipboard = activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("TextView","${binding.edSentences.text}  -  ${binding.tvResultTranslate.text}")
            clipboard.setPrimaryClip(clip)
            Toast.makeText(requireContext(),"Copied",Toast.LENGTH_SHORT).show()
        }



    }

}