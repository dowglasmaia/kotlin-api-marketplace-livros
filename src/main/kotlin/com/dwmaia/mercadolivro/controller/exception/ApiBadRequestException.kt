package com.dwmaia.mercadolivro.controller.exception

class ApiBadRequestException(
        override val message: String,
        val errorCode: String) : Exception() {}