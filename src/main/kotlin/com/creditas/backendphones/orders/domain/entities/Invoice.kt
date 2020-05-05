package com.creditas.backendphones.orders.domain.entities


import com.creditas.backendphones.user.domain.entities.ShopUser
import com.fasterxml.jackson.annotation.JsonManagedReference
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table
data class Invoice(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Int?,

        @JsonManagedReference(value = "invoice-user")
        @ManyToOne
        @JoinColumn
        var shopUser: ShopUser,

        val data: LocalDateTime = LocalDateTime.now(),

        @JsonManagedReference(value = "invoice-order_item")
        @OneToMany (mappedBy = "invoice", cascade = [(CascadeType.ALL)], orphanRemoval = true)
        var order_items: MutableList<OrderItem> = mutableListOf()
)