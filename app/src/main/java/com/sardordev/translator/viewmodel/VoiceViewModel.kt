package com.sardordev.translator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sardordev.translator.data.model.BodyTranslate
import com.sardordev.translator.domain.apprepository.AppRepository
import com.sardordev.translator.utils.ResponseEvent
import com.sardordev.translator.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VoiceViewModel @Inject constructor(private val appRepository: AppRepository) : ViewModel() {
    private val _wordsobserver = MutableStateFlow<UiEvent>(UiEvent.Empty)
    val wordobserver: StateFlow<UiEvent> get() = _wordsobserver


    fun transaleteWord(bodyTranslate: BodyTranslate) {
        _wordsobserver.value = UiEvent.Loading
        viewModelScope.launch {
            val result = appRepository.traslateWord(bodyTranslate)
            when (result) {
                is ResponseEvent.Error -> {
                    _wordsobserver.value = UiEvent.Error(message = result.message!!)
                }
                is ResponseEvent.Success -> {
                    _wordsobserver.value = UiEvent.Success(result.data)
                }
            }
        }
    }

}