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
        var password: String?,
        var idLastPhonePurchased: Int?,
        var idLastPhonePurchasedVersion: Int?,
        var idLastPhonePurchasedColor: Int?
)