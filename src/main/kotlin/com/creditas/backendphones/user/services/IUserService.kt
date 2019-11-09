package com.creditas.backendphones.user.services

import com.creditas.backendphones.phone.domain.entities.Phone
import java.util.*

interface IUserService {

    fun ifUserExist(email:String, password:String):Boolean
    fun setUsersExample()

}