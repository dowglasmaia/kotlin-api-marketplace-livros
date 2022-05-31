package com.dwmaia.mercadolivro.controller.response.customer

import com.dwmaia.mercadolivro.model.enums.CostumerStatus

data class CustomerResponse(
        var id: Int,
        var name: String,
        var email: String,
        var status: CostumerStatus
)