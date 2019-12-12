package com.creditas.backendphones.product.domain.dao

import com.creditas.backendphones.product.domain.entities.Storage
import org.springframework.data.repository.CrudRepository

interface IStorageDao: CrudRepository<Storage, Int> {

}