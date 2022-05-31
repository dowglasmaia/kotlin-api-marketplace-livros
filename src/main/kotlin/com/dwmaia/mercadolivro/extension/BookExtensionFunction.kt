package com.dwmaia.mercadolivro.extension

import com.dwmaia.mercadolivro.controller.request.book.PostBookRequestDTO
import com.dwmaia.mercadolivro.controller.request.book.PutBookRequestDTO
import com.dwmaia.mercadolivro.controller.response.book.BookModelResponse
import com.dwmaia.mercadolivro.model.BookModel
import com.dwmaia.mercadolivro.model.CustomerModel
import com.dwmaia.mercadolivro.model.enums.BookStatus

fun PostBookRequestDTO.toBookModel(customer: CustomerModel): BookModel {
    return BookModel(
            name = this.name,
            price = this.price,
            status = BookStatus.ATIVO,
            customer = customer
    )
}


fun PutBookRequestDTO.toBookModel(previousValue: BookModel): BookModel {
    return BookModel(
            id = previousValue.id,
            name = this.name ?: previousValue.name,  // if ternario KOTLIN
            price = this.price,
            status = previousValue.status,
            customer = previousValue.customer
    )
}

fun BookModel.toResponse(): BookModelResponse {
    return BookModelResponse(
            id = this.id,
            name = this.name,
            customer = this.customer,
            status = this.status,
            price = this.price
    )
}

