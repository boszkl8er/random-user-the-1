package com.example.hexagonal.service

import com.example.hexagonal.dao.UserDaoH2
import com.example.hexagonal.model.UserModel
import com.example.hexagonal.util.getLogger
import com.fasterxml.jackson.databind.JsonNode
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class UserService (
    private val restTemplate: RestTemplate,
    private val userDaoH2: UserDaoH2
){
    private val log = getLogger { }

    fun getAllUser(): List<UserModel> = userDaoH2.getAll()

    fun getAllUserBySeed(seed: String): List<UserModel> = userDaoH2.getAllBySeed(seed)

    fun getAllRandomUserBySeed(seed: String?):List<UserModel> = getUserBySeed(seed)

    internal fun getUserBySeed(seed: String?): List<UserModel>{
        // 1.Get User From API
        val result = getUserFromApi(seed)
        result ?: throw Exception("Error cannot get user from API")

        // 2.Convert from json to list model
        val users = convertToUser(result)

        // 3.Save to DB
        userDaoH2.saveAll(users)

        // 4.Find all from DB
        return if(seed != null) {
            userDaoH2.getAllBySeed(seed)
        } else {
            userDaoH2.getAll()
        }
    }

    internal fun convertToUser(jsonResult: JsonNode): List<UserModel> {
        jsonResult.get("results")?: throw Exception("Error result is null")
        if(jsonResult.get("results").size() == 0) throw Exception("Error result is empty")

        val users: MutableList<UserModel> = mutableListOf()
        var seed: String
        for(json: JsonNode in jsonResult.get("results")){
            try{
                seed = jsonResult.get("info").get("seed").textValue()
            } catch(e: Exception){
                seed = ""
            }

            val user = UserModel(
                gender = json.get("gender").textValue(),
                title = json.get("name").get("title").textValue(),
                first = json.get("name").get("first").textValue(),
                last = json.get("name").get("last").textValue(),
                seed = seed
            )
            users.add(user)
        }

        return users
    }

    internal fun getUserFromApi(seed: String?):JsonNode? {
        var endpoint = "https://randomuser.me/api/"
        if(seed != null){
             endpoint = "https://randomuser.me/api/?seed=".plus(seed)
        }

        try {
            val applicationResponseModel = restTemplate.exchange(
                endpoint,
                HttpMethod.GET,
                HttpEntity<JsonNode>(HttpHeaders()),
                object : ParameterizedTypeReference<JsonNode>() {}
            )
            return applicationResponseModel.body
        } catch (e: Exception) {
            log.error("Error when call losClient get application")
            throw e
        }
    }
}