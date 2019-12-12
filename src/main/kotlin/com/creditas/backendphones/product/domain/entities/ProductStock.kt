package com.creditas.backendphones.product.domain.entities


import com.fasterxml.jackson.annotation.JsonManagedReference
import javax.persistence.*

@Entity
@Table
data class ProductStock(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Int?,
        var stock: Int,
        var sold_out: Int,
        val price: Float,

        @JsonManagedReference
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn
        val model: Model,
        @JsonManagedReference
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn
        var brand: Brand,
        @JsonManagedReference
        @ManyToOne
        @JoinColumn
        var color: Color,
        @JsonManagedReference
        @ManyToOne
        @JoinColumn
        var ram: Ram,
        @JsonManagedReference
        @ManyToOne
        @JoinColumn
        var storage: Storage
)