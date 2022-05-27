package com.dwmaia.mercadolivro.events.listener

import com.dwmaia.mercadolivro.events.PurchaseEvent
import com.dwmaia.mercadolivro.service.BookService
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class UpdateStatusBookListener(
        private val bookService: BookService
) {
    @Async
    @EventListener
    fun listene(purchaseEvent: PurchaseEvent){
        println("Atualizando Status do book")
        bookService.purchase(purchaseEvent.purchaseModel.books)
    }

}