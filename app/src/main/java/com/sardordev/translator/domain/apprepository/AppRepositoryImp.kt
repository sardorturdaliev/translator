package com.sardordev.translator.domain.apprepository

import androidx.lifecycle.LiveData
import com.sardordev.translator.data.api.TransApi
import com.sardordev.translator.data.database.DataBaseSaved
import com.sardordev.translator.data.entity.SavedWords
import com.sardordev.translator.data.model.BodyTranslate
import com.sardordev.translator.data.model.ResultTranslated
import com.sardordev.translator.utils.ResponseEvent
import javax.inject.Inject


class AppRepositoryImp @Inject constructor(
    private val api: TransApi,
    private val dataBaseSaved: DataBaseSaved
) : AppRepository {


    override suspend fun traslateWord(bodyTranslate: BodyTranslate): ResponseEvent<List<ResultTranslated>> {
        return try {
            val result = api.translateWords(bodyTranslate)
            if (result.isSuccessful) {
                ResponseEvent.Success(result.body())
            } else {
                ResponseEvent.Error(result.message())
            }
        } catch (e: Exception) {
            ResponseEvent.Error(e.message)
        }
    }

    override fun insertSaved(savedWords: SavedWords) {
        dataBaseSaved.getSavedDao().insertData(savedWords)
    }

    override fun getAllWords(): LiveData<List<SavedWords>> {
        return dataBaseSaved.getSavedDao().getAllSaved()
    }


}