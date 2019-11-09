package com.creditas.backendphones.phone.domain.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import javax.persistence.*

@Entity
@Table(name = "color_phone")
data class ColorPhone(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "id_color_phone")
        val idColorPhone: Int?,
        val color: String?,
        @JsonBackReference
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "PHONE_ID")
        val phone_model: Phone
)