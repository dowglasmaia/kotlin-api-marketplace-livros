package com.dwmaia.mercadolivro.model

import com.dwmaia.mercadolivro.exception.ApiBadRequestException
import com.dwmaia.mercadolivro.exception.enums.EnumErros
import com.dwmaia.mercadolivro.model.enums.BookStatus
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
    var status: BookStatus? = null
        set(value) {
            if (field == BookStatus.CANCELADO || field == BookStatus.DELETADO) {
                throw ApiBadRequestException(EnumErros.ML1004.message.format(field), EnumErros.ML1004.statusCode)
            }
            field = value
        }

    constructor(
            id: Int? = null,
            name: String,
            price: BigDecimal,
            customer: CustomerModel? = null,
            status: BookStatus?
    ) : this(id, name, price, customer) {
        this.status = status
    }
}