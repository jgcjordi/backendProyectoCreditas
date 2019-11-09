package com.creditas.backendphones.phone.domain.entities.dao

import com.creditas.backendphones.phone.domain.entities.ColorPhone
import org.springframework.data.repository.CrudRepository

interface IColorPhoneDao: CrudRepository<ColorPhone, Int> {

}