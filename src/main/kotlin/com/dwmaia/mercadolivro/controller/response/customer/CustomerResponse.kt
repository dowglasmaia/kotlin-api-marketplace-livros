package com.dwmaia.mercadolivro.controller.response.customer

import com.dwmaia.mercadolivro.model.enums.CustomerStatus

data class CustomerResponse(
        var id: Int,
        var name: String,
        var email: String,
        var status: CustomerStatus
)