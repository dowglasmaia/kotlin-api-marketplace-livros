package com.dwmaia.mercadolivro.controller.exception

import com.dwmaia.mercadolivro.controller.response.error.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest


@ControllerAdvice
class HandlerException {

    @ExceptionHandler(ApiNotFoundException::class)
    fun handlerNotFoundException(ex: ApiNotFoundException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.message ?: "",
                internalCode = ex.errorCode,
                erros = null
        )
        return ResponseEntity(error, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(ApiBadRequestException::class)
    fun handlerBadRequestException(ex: ApiBadRequestException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.message ?: "",
                internalCode = ex.errorCode,
                erros = null
        )
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }
}
