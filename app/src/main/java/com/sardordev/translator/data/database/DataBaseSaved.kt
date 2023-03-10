package com.sardordev.translator.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sardordev.translator.data.dao.SavedDao
import com.sardordev.translator.data.entity.SavedWords

@Database(entities = [SavedWords::class], version = 1)
abstract class DataBaseSaved : RoomDatabase() {
    abstract fun getSavedDao(): SavedDao


    companion object {
      private  var instance: DataBaseSaved? = null

        fun init(context: Context) {
            if (instance == null) {
                instance = Room.databaseBuilder(context, DataBaseSaved::class.java, "svdb")
                    .allowMainThreadQueries().build()
            }
        }
        fun getInstance()  = instance!!
    }


}