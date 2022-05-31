package com.dwmaia.mercadolivro.controller.response.book

import com.dwmaia.mercadolivro.model.CustomerModel
import com.dwmaia.mercadolivro.model.enums.BookStatus
import java.math.BigDecimal

data class BookModelResponse(
        var id: Int? = null,
        var name: String,
        var customer: CustomerModel? = null,
        var status: BookStatus? = null,
        var price: BigDecimal,
)
