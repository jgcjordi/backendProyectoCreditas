package com.creditas.backendphones.user.services

import com.creditas.backendphones.product.domain.entities.ProductStock
import com.creditas.backendphones.user.domain.entities.User
import javax.servlet.http.HttpServletRequest

interface IUserService {

    fun ifUserExist(email: String, password: String): Boolean
    fun ifEmailExist(email: String): Boolean
    fun getUserByEmail(email: String): User
    fun getUserById(id: Int): User
    fun registryNewUser(user: User): User
    fun getJWTToken(email: String, request: HttpServletRequest): String
    fun getUserFromBearer(bearer: String): User

    fun setBdUsersExample()

}