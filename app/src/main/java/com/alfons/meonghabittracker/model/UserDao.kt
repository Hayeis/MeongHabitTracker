package com.alfons.meonghabittracker.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
    @Dao
    interface UserDao {
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insertUser(user: User): Long
        @Query("SELECT * FROM User WHERE username = :username AND password = :password LIMIT 1")
        suspend fun login(username: String, password: String): User?
    }