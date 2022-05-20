package com.dwmaia.mercadolivro.controller.request.book

import java.math.BigDecimal
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

class PutBookRequestDTO(
        @field:NotBlank(message = "The Name Of The Book is Required")
        var name: String,

        @field:NotNull(message = "The Price The Book is Required")
        var price: BigDecimal,

)
