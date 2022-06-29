package com.example.hexagonal.api

import com.example.hexagonal.model.UserModel
import com.example.hexagonal.service.UserService
import org.springframework.stereotype.Component

@Component
class HttpApi(
    private val userService: UserService
): ApiInterface {
    override fun getAllRandomUserBySeed(seed: String?): List<UserModel> {
        return userService.getAllRandomUserBySeed(seed)
    }
}