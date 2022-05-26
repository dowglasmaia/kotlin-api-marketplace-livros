package com.dwmaia.mercadolivro.controller.mapper

import com.dwmaia.mercadolivro.controller.request.purchase.PostPurchaseRequest
import com.dwmaia.mercadolivro.model.PurchaseModel
import com.dwmaia.mercadolivro.service.BookService
import com.dwmaia.mercadolivro.service.CustomerService
import org.springframework.stereotype.Component

@Component
class PurchaseMapper(
        private val bookService: BookService,
        private val customerService: CustomerService
) {
    fun toModel(request: PostPurchaseRequest): PurchaseModel {
        val customer = customerService.findById(request.customerId)
        val books = bookService.findAllByIds(request.bookIds)

        return PurchaseModel(
                customer = customer,
                books = books,
                price = books.sumOf { it.price }
        )
    }


}