package com.dwmaia.mercadolivro.controller.response.error

data class ErrorResponse(
        var httpCode: Int,
        var message: String,
        var internalCode: String,
        var erros: List<FieldErrorResponse>?
)
