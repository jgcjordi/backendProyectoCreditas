package com.creditas.backendphones.product.domain.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.*
import javax.transaction.Transactional

@Entity
@Table
@Transactional
data class Model(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Int?,
        val name: String,
        @Lob//-->Texto largo
        val description: String,
        val image: String,

        @JsonBackReference
        @OneToMany(mappedBy = "model", cascade = [(CascadeType.ALL)], orphanRemoval = true)
        var productStock: List<ProductStock> = listOf()
)