package com.thanushaw.practice1.api

import android.provider.ContactsContract

data class User(
    val id:Number,
    val username:String,
    val name:String,
    val email:String,
    val address:Address,
    val phone:String,
    val website: String,
    val company:Company

    )