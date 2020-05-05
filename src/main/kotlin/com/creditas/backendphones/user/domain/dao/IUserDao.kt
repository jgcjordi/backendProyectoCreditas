package com.creditas.backendphones.user.domain.dao

import com.creditas.backendphones.user.domain.entities.ShopUser
import org.springframework.data.repository.CrudRepository

interface IUserDao: CrudRepository<ShopUser, Int> {
    fun findByEmail(email:String):ShopUser
    fun existsByEmail(email:String):Boolean
}