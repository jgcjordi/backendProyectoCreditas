package com.creditas.backendphones.product.domain.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.*

@Entity
@Table
data class Brand(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Int?,
        val name: String,

        @JsonBackReference
        @OneToMany(mappedBy = "brand", cascade = [(CascadeType.ALL)], orphanRemoval = true)
        var productStock: List<ProductStock> = listOf()
)