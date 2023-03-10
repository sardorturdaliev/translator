package com.sardordev.translator.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sardordev.translator.data.entity.SavedWords

@Dao
interface SavedDao {

    @Insert
    fun insertData(savedWords: SavedWords)


    @Query("select *  from  savedtable")
    fun getAllSaved(): LiveData<List<SavedWords>>


}