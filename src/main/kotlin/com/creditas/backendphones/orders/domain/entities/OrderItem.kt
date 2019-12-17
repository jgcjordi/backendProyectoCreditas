package com.creditas.backendphones.orders.domain.entities

import com.creditas.backendphones.product.domain.entities.ProductStock
import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table
data class OrderItem(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Int?,

        @JsonManagedReference
        @ManyToOne
        @JoinColumn
        val order_item:ProductStock,

        @JsonBackReference(value = "invoice-order_item")
        @ManyToOne
        @JoinColumn
        val invoice:Invoice
)