package com.example.hexagonal.model

import java.io.Serializable

class ResponseStatus(
    var code: String,
    var message: String? = null,
    var description: String? = null
): Serializable {
    override fun toString(): String {
        return "ResponseStatus(code=$code, message=$message, description=$description)"
    }
}