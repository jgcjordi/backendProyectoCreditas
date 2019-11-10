package com.creditas.backendphones.user.services

import com.creditas.backendphones.phone.domain.entities.Phone
import com.creditas.backendphones.user.domain.entities.User
import java.util.*

interface IUserService {

    fun ifUserExist(email: String, password: String): Boolean
    fun setUsersExample()
    fun getUserByEmail(email: String): User

}