package com.sardordev.translator.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.sardordev.translator.data.entity.SavedWords

@Dao
interface SavedDao {

    @Insert
    fun insertData(savedWords: SavedWords)


    @Query("delete from savedtable")
    fun deleteAll()

    @Query("select *  from  savedtable")
    fun getAllSaved(): LiveData<List<SavedWords>>


}