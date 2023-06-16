package com.reydinata.capstoneproject.ui.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0 ,
    @ColumnInfo(name = "username")
    var username: String = "",
    @ColumnInfo(name = "password")
    var password: String = "",
    @ColumnInfo(name = "berat")
    var berat: Int = 0,
    @ColumnInfo(name = "tinggi")
    var tinggi: Int = 0,
    @ColumnInfo(name = "rasioolahraga")
    var rasioolahraga: String="",
) : Parcelable

