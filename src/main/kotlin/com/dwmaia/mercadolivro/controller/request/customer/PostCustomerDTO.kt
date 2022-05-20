package com.dwmaia.mercadolivro.controller.request.customer

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty

data class PostCustomerDTO(

        @field:NotBlank(message = "The Name Of The Customer Is Required")
        var name: String,

        @field:NotEmpty(message = "The Email Is Required")
        @field:Email
        var email: String
)