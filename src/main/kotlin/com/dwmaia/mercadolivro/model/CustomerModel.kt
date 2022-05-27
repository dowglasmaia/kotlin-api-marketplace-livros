package com.dwmaia.mercadolivro.model

import com.dwmaia.mercadolivro.model.enums.Roles
import javax.persistence.*


@Entity(name = "customer")
data class CustomerModel(

        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int? = null,

        @Column
        var name: String? = null,

        @Column(unique = true)
        var email: String,

        @Column
        // @Enumerated(EnumType.STRING)
        var status: String,

        @Column
        var password: String,

        @Column(name = "role")
        @Enumerated(EnumType.STRING)
        @ElementCollection(targetClass = Roles::class, fetch = FetchType.EAGER)
        @CollectionTable(name = "customer_roles", joinColumns = [JoinColumn(name = "customer_id")])
        var roles: Set<Roles> = setOf()



)