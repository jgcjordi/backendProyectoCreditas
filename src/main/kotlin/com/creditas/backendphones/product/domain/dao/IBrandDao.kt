package com.creditas.backendphones.product.domain.dao

import com.creditas.backendphones.product.domain.entities.Brand
import org.springframework.data.repository.CrudRepository

interface IBrandDao: CrudRepository<Brand, Int> {

}