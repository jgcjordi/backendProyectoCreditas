package com.creditas.backendphones.user.domain.entities


import javax.persistence.*

@Entity
@Table(name = "user")
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name="id_user")
        val id_user: Int?,
        val email:String?,
        val name:String?,
        val password: String?,
        val idLastPhonePurchased: Int?,
        val idLastPhonePurchasedVersion: Int?,
        val idLastPhonePurchasedColor: Int?
)