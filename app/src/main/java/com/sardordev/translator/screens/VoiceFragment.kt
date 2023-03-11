package com.sardordev.translator.screens

import android.app.Activity.RESULT_OK
import android.content.*
import android.os.Bundle
import android.speech.RecognizerIntent
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
import com.sardordev.translator.data.entity.SavedWords
import com.sardordev.translator.data.model.BodyTranslate
import com.sardordev.translator.data.model.ResultTranslated
import com.sardordev.translator.databinding.FragmentVoiceBinding
import com.sardordev.translator.utils.UiEvent
import com.sardordev.translator.viewmodel.VoiceViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class VoiceFragment : Fragment() {
    private val binding by lazy { FragmentVoiceBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<VoiceViewModel>()
    private var dan = "ru"
    private var gacha = "uz"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.btntranslate.setOnClickListener {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            try {
                startActivityForResult(intent, 1)
            } catch (a: ActivityNotFoundException) {
                Toast.makeText(requireContext(), "Something with Wrong", Toast.LENGTH_SHORT).show()
            }
        }


        translatedWordObserver()
        iconfunctions()
        changeLanguage()


        return binding.root
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            1 -> {
                if (resultCode == RESULT_OK) {
                    val result: ArrayList<String> =
                        data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)!!
                    binding.edSentences.setText(result[0])
                    viewModel.transaleteWord(
                        BodyTranslate(
                            "$dan",
                            arrayListOf(binding.edSentences.text.toString()),
                            arrayListOf("$gacha")
                        )
                    )
                }
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

    private fun iconfunctions() {
        binding.imgtrash.setOnClickListener {
            binding.edSentences.text = ""
            binding.tvResultTranslate.text = ""
        }
        binding.imgcopy.setOnClickListener {
            val clipboard =
                activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText(
                "TextView",
                "${binding.edSentences.text}  -  ${binding.tvResultTranslate.text}"
            )
            clipboard.setPrimaryClip(clip)
            Toast.makeText(requireContext(), "Copied", Toast.LENGTH_SHORT).show()
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

}