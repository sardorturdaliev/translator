package com.sardordev.translator.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sardordev.translator.data.entity.SavedWords
import com.sardordev.translator.domain.apprepository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SavedViewModel @Inject constructor(private val appRepository: AppRepository) : ViewModel() {
    val getSavedWords: LiveData<List<SavedWords>> = appRepository.getAllWords()

    fun deleteAllItems(){
        appRepository.deleteAll()
    }


}