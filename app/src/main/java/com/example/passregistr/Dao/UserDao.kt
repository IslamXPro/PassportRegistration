package com.example.passregistr.Dao

import androidx.room.*
import com.example.passregistr.Entity.User

@Dao
interface UserDao {
    @Query("select * from user")
    fun getAllUser(): List<User>

    @Insert
    fun addUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Update
    fun updateUser(user: User)

    @Query("select * from user where id=:id")
    fun getUserById(id: Int): User
}
