package com.creditas.backendphones.product.domain.entities


import com.creditas.backendphones.orders.domain.entities.OrderItem
import com.fasterxml.jackson.annotation.JsonBackReference
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
        @ManyToOne
        @JoinColumn
        val model: Model,
        @JsonManagedReference
        @ManyToOne
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
        var storage: Storage,

        @JsonBackReference
        @OneToMany(mappedBy = "order_item", cascade = [(CascadeType.ALL)], orphanRemoval = true)
        var order_items: List<OrderItem> = listOf()


)