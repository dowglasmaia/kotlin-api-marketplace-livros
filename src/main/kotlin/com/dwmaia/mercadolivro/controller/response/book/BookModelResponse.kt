package com.dwmaia.mercadolivro.controller.response.book

import com.dwmaia.mercadolivro.model.CustomerModel
import java.math.BigDecimal

data class BookModelResponse(
        var id: Int? = null,
        var name: String,
        var customer: CustomerModel? = null,
        var status: String? = null,
        var price: BigDecimal,
)
