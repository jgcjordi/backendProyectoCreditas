package com.creditas.backendphones.user.services

import com.creditas.backendphones.user.domain.entities.ShopUser
import javax.servlet.http.HttpServletRequest

interface IUserService {

    fun ifUserExist(email: String, password: String): Boolean
    fun ifEmailExist(email: String): Boolean
    fun getUserByEmail(email: String): ShopUser
    fun getUserById(id: Int): ShopUser
    fun registryNewUser(shopUser: ShopUser): ShopUser
    fun getJWTToken(email: String, request: HttpServletRequest): String
    fun getUserFromBearer(bearer: String): ShopUser

    fun setBdUsersExample()

}