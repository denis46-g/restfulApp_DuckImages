package com.example.restfulapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ducks")
data class Duck(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "http") val http: Boolean?,
    @ColumnInfo(name = "code") val code: String?,
    @ColumnInfo(name = "filetype") val filetype: String?
)