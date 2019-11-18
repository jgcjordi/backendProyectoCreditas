package com.creditas.backendphones.phone.domain.dao

import com.creditas.backendphones.phone.domain.entities.Phone
import com.creditas.backendphones.phone.domain.entities.VersionPhone
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository

interface IPhoneDao: CrudRepository<Phone, Int> {

    fun findAll(pageable: Pageable): MutableList<Phone>

}