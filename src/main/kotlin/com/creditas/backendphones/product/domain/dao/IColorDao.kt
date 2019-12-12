package com.creditas.backendphones.product.domain.dao

import com.creditas.backendphones.product.domain.entities.Color
import org.springframework.data.repository.CrudRepository

interface IColorDao: CrudRepository<Color, Int> {

}