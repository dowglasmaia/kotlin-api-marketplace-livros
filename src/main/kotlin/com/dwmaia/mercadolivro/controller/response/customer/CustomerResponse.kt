package com.dwmaia.mercadolivro.controller.response.customer

data class CustomerResponse(
        var id: Int,
        var name: String,
        var email: String,
        var status: String
)