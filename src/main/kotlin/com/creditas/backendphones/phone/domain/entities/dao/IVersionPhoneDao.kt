package com.creditas.backendphones.phone.domain.entities.dao

import com.creditas.backendphones.phone.domain.entities.ColorPhone
import com.creditas.backendphones.phone.domain.entities.Phone
import com.creditas.backendphones.phone.domain.entities.VersionPhone
import org.springframework.data.repository.CrudRepository

interface IVersionPhoneDao: CrudRepository<VersionPhone, Int> {

}