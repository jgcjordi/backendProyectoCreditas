package com.creditas.backendphones.user.domain.entities.dao

import com.creditas.backendphones.user.domain.entities.User
import org.springframework.data.repository.CrudRepository

interface IUserDao: CrudRepository<User, Int> {
    fun findByEmail(email:String):User
    fun existsByEmail(email:String):Boolean
}