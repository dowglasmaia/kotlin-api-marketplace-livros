package com.dwmaia.mercadolivro.model

import com.dwmaia.mercadolivro.controller.exception.ApiBadRequestException
import com.dwmaia.mercadolivro.controller.exception.enums.EnumErros
import java.math.BigDecimal
import javax.persistence.*


@Entity(name = "book")
data class BookModel(

        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int? = null,

        @Column
        var name: String,

        @Column
        var price: BigDecimal,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "customer_id")
        var customer: CustomerModel? = null

) {
    @Column
    var status: String? = null
        set(value) {
            if (field == "CANCELADO" || field == "DELETADO") {
                throw ApiBadRequestException(EnumErros.ML1004.message.format(field), EnumErros.ML1004.statusCode)
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