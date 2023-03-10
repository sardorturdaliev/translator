package com.sardordev.translator.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "savedtable")
data class SavedWords(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    @ColumnInfo(name = "lanfrom")
    val lanfrom:String,
    @ColumnInfo(name = "lanto")
    val lanto:String,
    @ColumnInfo(name = "translateW")
    val traslateW:String,
    @ColumnInfo(name = "translatedW")
    val translatedW:String
)
