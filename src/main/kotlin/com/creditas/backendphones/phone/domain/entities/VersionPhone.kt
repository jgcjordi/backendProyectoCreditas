package com.creditas.backendphones.phone.domain.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.*

@Entity
@Table(name = "version_phone")
data class VersionPhone(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "id_version_phone")
        val id_version_phone: Int?,
        val price: Float?,
        val ram: Int?,
        val storage: Int?,
        @JsonBackReference
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "PHONE_ID")
        val phone_model: Phone
)

