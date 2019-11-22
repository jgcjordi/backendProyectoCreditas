package com.creditas.backendphones.user.services

import com.creditas.backendphones.phone.domain.entities.Phone
import com.creditas.backendphones.user.domain.entities.User
import java.util.*
import javax.servlet.http.HttpServletRequest

interface IUserService {

    fun ifUserExist(email: String, password: String): Boolean
    fun ifEmailExist(email: String): Boolean
    fun getUserByEmail(email: String): User
    fun registryNewUser(user:User):User
    fun purchasePhone(idUser:Int, idPhone: Int, idVersion: Int, idColor: Int):User
    fun getJWTToken(email:String, request: HttpServletRequest):String
    fun setUsersExample()

}