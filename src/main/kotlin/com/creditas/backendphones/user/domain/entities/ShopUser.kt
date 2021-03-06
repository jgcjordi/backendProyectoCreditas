package com.creditas.backendphones.user.domain.entities


import com.creditas.backendphones.orders.domain.entities.Invoice
import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.*

@Entity
@Table
data class ShopUser(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Int?,
        val email: String?,
        val name: String?,
        var password: String?,

        @JsonBackReference(value = "invoice-user")
        @OneToMany(mappedBy = "shopUser", cascade = [(CascadeType.ALL)], orphanRemoval = true)
        var invoices: List<Invoice> = listOf()
)