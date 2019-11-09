package com.creditas.backendphones.phone.domain.entities


import com.fasterxml.jackson.annotation.JsonManagedReference
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import javax.persistence.*

@Entity
@Table(name = "phone")
data class Phone(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name="id_phone")
        val id_phone: Int?,
        val brand:String?,
        val model: String?,
        val data: String?,
        val src: String?,
        @JsonManagedReference
        @OneToMany(mappedBy = "phone_model", cascade = [CascadeType.ALL])
        var versions: List<VersionPhone>?,
        @JsonManagedReference
        @OneToMany(mappedBy = "phone_model", cascade = [CascadeType.ALL])
        var colors: List<ColorPhone>?
)