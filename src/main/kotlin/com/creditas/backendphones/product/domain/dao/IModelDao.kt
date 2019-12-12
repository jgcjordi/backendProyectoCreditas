package com.creditas.backendphones.product.domain.dao

import com.creditas.backendphones.product.domain.entities.Model
import org.springframework.data.repository.CrudRepository

interface IModelDao: CrudRepository<Model, Int> {

}