package com.dwmaia.mercadolivro.model

import org.springframework.format.annotation.NumberFormat
import org.springframework.format.annotation.NumberFormat.Style.CURRENCY
import java.math.BigDecimal
import javax.persistence.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull


@Entity(name = "book")
data class BookModel(

        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int? = null,

        @Column
        @NotEmpty(message = "The Name Of The Book Is Required")
        var name: String,

        @Column
        @NotNull
        @NumberFormat(style = CURRENCY, pattern = "#,##0.00")
        var price: BigDecimal,

        @Column
        var status: String? = null,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "customer_id")
        var customer: CustomerModel? = null

)