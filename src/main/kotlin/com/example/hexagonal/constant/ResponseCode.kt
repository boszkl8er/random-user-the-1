package com.example.hexagonal.constant

import com.example.hexagonal.model.ResponseStatus

object ResponseCode {
    val CODE200 = ResponseStatus("200", "Success", "Success")
    val CODE400 = ResponseStatus("400", "Bad request", "Missing/Invalid argument")
    val CODE500 = ResponseStatus("500", "Internal Error", "Internal Error")

    fun resolveFormat(responseStatus: ResponseStatus, vararg word : String): ResponseStatus {
        return ResponseStatus(responseStatus.code, responseStatus.message?.format(*word), responseStatus.description?.format(*word))
    }
}