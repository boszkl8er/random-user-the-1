package com.example.hexagonal.repository

import com.example.hexagonal.model.UserModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<UserModel, Int> {
    fun findBySeed(seed: String): List<UserModel>
}