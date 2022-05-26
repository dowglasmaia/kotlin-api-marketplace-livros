package com.dwmaia.mercadolivro.service

import com.dwmaia.mercadolivro.events.PurchaseEvent
import com.dwmaia.mercadolivro.model.PurchaseModel
import com.dwmaia.mercadolivro.repository.PurcharseRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class PurchaseService(
        private val purcharseRepository: PurcharseRepository,
        private val event: ApplicationEventPublisher
) {

    fun create(purchaseModel: PurchaseModel): PurchaseModel {
        val purchaseSavad =  purcharseRepository.save(purchaseModel)

        event.publishEvent(PurchaseEvent(this, purchaseSavad))

        return purchaseSavad;
    }

    fun update(purchaseModel: PurchaseModel) {
        purcharseRepository.save(purchaseModel)
    }

}
