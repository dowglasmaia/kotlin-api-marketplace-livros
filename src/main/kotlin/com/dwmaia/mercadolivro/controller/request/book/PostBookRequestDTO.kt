package com.dwmaia.mercadolivro.controller.request.book

import com.fasterxml.jackson.annotation.JsonAlias
import java.math.BigDecimal
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class PostBookRequestDTO(

        @field:NotBlank(message = "The Name Of The Book is Required")
        var name: String,

        @field:NotNull(message = "The Price The Book is Required")
        var price: BigDecimal,

        @JsonAlias("customer_id")
        var customerId: Int

)
