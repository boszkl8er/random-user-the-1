package com.example.hexagonal.dao

import com.example.hexagonal.model.UserModel

interface UserDaoInterface {
    fun getAll(): List<UserModel>
    fun getAllBySeed(seed: String): List<UserModel>
    fun saveAll(users: List<UserModel>): List<UserModel>
}