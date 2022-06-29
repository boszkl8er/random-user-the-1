package com.example.hexagonal.api

import com.example.hexagonal.model.UserModel

interface ApiInterface {
    fun getAllRandomUserBySeed(seed: String?): List<UserModel>
}