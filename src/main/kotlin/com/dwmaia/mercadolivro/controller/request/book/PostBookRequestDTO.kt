package com.dwmaia.mercadolivro.controller.request.book

import com.fasterxml.jackson.annotation.JsonAlias
import java.math.BigDecimal

data class PostBookRequestDTO(

        var name: String,

        var price: BigDecimal,

        @JsonAlias("customer_id")
        var customerId: Int

)
