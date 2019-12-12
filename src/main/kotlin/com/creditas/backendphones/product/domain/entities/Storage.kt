package com.creditas.backendphones.product.domain.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.*

@Entity
@Table
data class Storage(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Int?,
        val value: Int,

        @JsonBackReference
        @OneToMany(mappedBy = "storage", fetch = FetchType.LAZY, cascade = [(CascadeType.ALL)], orphanRemoval = true)
        var productStock: List<ProductStock>
)