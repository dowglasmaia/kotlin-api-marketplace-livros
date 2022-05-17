package com.dwmaia.mercadolivro.extension

import com.dwmaia.mercadolivro.controller.request.book.PostBookRequestDTO
import com.dwmaia.mercadolivro.controller.request.book.PutBookRequestDTO
import com.dwmaia.mercadolivro.model.BookModel
import com.dwmaia.mercadolivro.model.CustomerModel


fun PostBookRequestDTO.toBookModel(customer: CustomerModel): BookModel {
    return BookModel(
            name = this.name,
            price = this.price,
            status = "ATIVO",
            customer = customer
    )
}

fun PutBookRequestDTO.toBookModel(book:BookModel): BookModel {
    return BookModel(
            id = book.id,
            name = this.name,
            price = this.price,
            status = book.status,
            customer = book.customer
    )
}

