package com.reydinata.capstoneproject.ui.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(users : User)
    @Update
    fun update(users: User)
    @Query("SELECT * from user where username = :username")
    fun getUserByUsername(username: String): LiveData<User>
}