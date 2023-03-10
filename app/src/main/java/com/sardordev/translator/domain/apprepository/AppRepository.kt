package com.sardordev.translator.domain.apprepository

import androidx.lifecycle.LiveData
import com.sardordev.translator.data.entity.SavedWords
import com.sardordev.translator.data.model.BodyTranslate
import com.sardordev.translator.data.model.ResultTranslated
import com.sardordev.translator.utils.ResponseEvent

interface AppRepository {
    suspend fun traslateWord(bodyTranslate: BodyTranslate): ResponseEvent<List<ResultTranslated>>

    fun insertSaved(savedWords: SavedWords)

    fun getAllWords() : LiveData<List<SavedWords>>

}