package com.dwmaia.mercadolivro.model

import com.dwmaia.mercadolivro.controller.exception.ApiBadRequestException
import com.dwmaia.mercadolivro.controller.exception.enums.Erros
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

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "customer_id")
        var customer: CustomerModel? = null

) {
    @Column
    var status: String? = null
        set(value) {
            if (field == "CANCELADO" || field == "DELETADO") {
                throw ApiBadRequestException(Erros.ML1004.message.format(field), Erros.ML1004.statusCode)
            }
            field = value
        }

    constructor(
            id: Int? = null,
            name: String,
            price: BigDecimal,
            customer: CustomerModel? = null,
            status: String?
    ) : this(id, name, price, customer) {
        this.status = status
    }
}