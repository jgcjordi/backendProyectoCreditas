package com.creditas.backendphones.phone.domain.entities.dao

import com.creditas.backendphones.phone.domain.entities.Phone
import org.springframework.data.repository.CrudRepository

interface IPhoneDao: CrudRepository<Phone, Int> {
    //public fun findBySipAndLastname()
    //public fun findBySip(sip:Long): Optional<Patient>
    //public fun deleteBySip(sip:Long): Int
}