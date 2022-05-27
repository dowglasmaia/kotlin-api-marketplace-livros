package com.dwmaia.mercadolivro.events.listener

import com.dwmaia.mercadolivro.events.PurchaseEvent
import com.dwmaia.mercadolivro.service.PurchaseService
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.util.*

@Component
class GenerateNfeListener(
        private val purchaseService: PurchaseService
) {
    @Async
    @EventListener
    fun listene(purchaseEvent: PurchaseEvent){
        println("Gerando Nota de Venda")
        val nfe = UUID.randomUUID().toString()
        val purchaseModel = purchaseEvent.purchaseModel.copy(nfe = nfe)

        purchaseService.update(purchaseModel)
    }

}