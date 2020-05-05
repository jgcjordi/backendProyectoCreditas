package com.creditas.backendphones.orders.domain.dao

import com.creditas.backendphones.orders.domain.entities.OrderItem
import org.springframework.data.repository.CrudRepository

interface IOrderItemDao: CrudRepository<OrderItem, Int> {
}