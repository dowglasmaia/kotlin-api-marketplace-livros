package com.dwmaia.mercadolivro.events

import com.dwmaia.mercadolivro.service.PurchaseService
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import java.util.*

@Component
class GenerateNfeListener(
        private val purchaseService: PurchaseService
) {
    @EventListener
    fun listene(purchaseEvent: PurchaseEvent){
        val nfe = UUID.randomUUID().toString()
        val purchaseModel = purchaseEvent.purchaseModel.copy(nfe = nfe)

        purchaseService.update(purchaseModel)
    }

}