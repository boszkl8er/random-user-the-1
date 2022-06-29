package com.example.hexagonal.dao

import com.example.hexagonal.model.UserModel
import com.example.hexagonal.repository.UserRepository
import org.springframework.stereotype.Component

@Component
class UserDaoH2 (
    private val userRepository: UserRepository
): UserDaoInterface {
    override fun getAll(): List<UserModel> {
        return userRepository.findAll()
    }

    override fun getAllBySeed(seed: String): List<UserModel> {
        return userRepository.findBySeed(seed)
    }

    override fun saveAll(users: List<UserModel>): List<UserModel> {
        return userRepository.saveAll(users)
    }
}