package com.creditas.backendphones.phone.domain.dao

import com.creditas.backendphones.phone.domain.entities.VersionPhone
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository

interface IVersionPhoneDao: CrudRepository<VersionPhone, Int> {
}