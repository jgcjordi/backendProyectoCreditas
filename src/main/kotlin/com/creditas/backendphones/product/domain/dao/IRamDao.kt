package com.creditas.backendphones.product.domain.dao

import com.creditas.backendphones.product.domain.entities.Ram
import org.springframework.data.repository.CrudRepository

interface IRamDao: CrudRepository<Ram, Int> {

}