package com.dwmaia.mercadolivro.exception

class ApiBadRequestException(
        override val message: String,
        val errorCode: String) : Exception() {}