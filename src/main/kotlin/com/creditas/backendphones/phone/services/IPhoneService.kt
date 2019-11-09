package com.creditas.backendphones.phone.services

import com.creditas.backendphones.phone.domain.entities.Phone

interface IPhoneService {

    fun getPhones():MutableList<Phone>
    fun setPhonesExample()

}