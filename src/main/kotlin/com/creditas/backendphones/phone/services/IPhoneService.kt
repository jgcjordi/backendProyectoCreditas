package com.creditas.backendphones.phone.services

import com.creditas.backendphones.phone.domain.entities.Phone
import java.util.*

interface IPhoneService {

    fun getAllPhones():MutableList<Phone>
    fun getPhoneById(id: Int): Optional<Phone>
    fun phonesFilteredByKeywords(stringFilter: String):MutableList<Phone>
    fun setPhonesExample()

    fun getAllPhonesPaginated(page:Int): MutableList<Phone>

}