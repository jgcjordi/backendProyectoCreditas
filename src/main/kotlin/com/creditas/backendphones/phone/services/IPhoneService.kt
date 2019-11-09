package com.creditas.backendphones.phone.services

import com.creditas.backendphones.phone.domain.entities.Phone
import java.util.*

interface IPhoneService {

    fun getAllPhones():MutableList<Phone>
    fun getPhoneById(id: Int): Optional<Phone>
    fun setPhonesExample()

}