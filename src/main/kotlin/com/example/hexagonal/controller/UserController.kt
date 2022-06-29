package com.example.hexagonal.controller

import com.example.hexagonal.api.HttpApi
import com.example.hexagonal.constant.ResponseCode
import com.example.hexagonal.model.ResponseModel
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1")
class UserController (
    private val httpApi: HttpApi
){
//    @GetMapping("/users")
//    fun getAllUser(@RequestParam seed: String?):ResponseEntity<ResponseModel<String>>{
//        return if (seed != null){
//            ResponseEntity.ok(ResponseModel(ResponseCode.CODE200, httpApi.getAllBySeed(seed)))
//        } else {
//            ResponseEntity.ok(ResponseModel(ResponseCode.CODE200, httpApi.getAll()))
//        }
//    }

    @GetMapping("/users")
    fun getAllRandomUserBySeed(@RequestParam seed: String?):ResponseEntity<ResponseModel<String>>
        = ResponseEntity.ok(ResponseModel(ResponseCode.CODE200, httpApi.getAllRandomUserBySeed(seed)))
}