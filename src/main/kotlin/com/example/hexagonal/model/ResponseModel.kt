package com.example.hexagonal.model

import java.io.Serializable

class ResponseModel<T>(
    var status: ResponseStatus? = null,
    var users: List<UserModel>? = null
): Serializable {
    constructor(dataObj: List<UserModel>?) : this(null, dataObj)
    constructor(code: String) : this(ResponseStatus(code))
    constructor(code: String, description: String) : this(ResponseStatus(code, null, description))
    constructor(code: String, message: String, description: String) : this(ResponseStatus(code, message, description))
}