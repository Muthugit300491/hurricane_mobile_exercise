package com.example.productapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "username")
    var username: String,
    @ColumnInfo(name = "password")
    var password: String,
    @ColumnInfo(name = "email")
    var email: String,
    @ColumnInfo(name = "isLogin")
    var isLogin:Boolean
)