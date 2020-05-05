package com.creditas.backendphones.orders.domain.dao

import com.creditas.backendphones.orders.domain.entities.Invoice
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface IInvoiceDao: CrudRepository<Invoice, Int> {

    @Query("SELECT * FROM creditasbackendbdv2.invoice where user_id = ?1 order by data desc limit 1",
            nativeQuery = true)
    fun findLastProductPurchasedByThisUserId(id: Int): Invoice

}