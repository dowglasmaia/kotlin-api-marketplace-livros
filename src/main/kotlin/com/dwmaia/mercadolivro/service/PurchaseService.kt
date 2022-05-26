package com.dwmaia.mercadolivro.service

import com.dwmaia.mercadolivro.model.PurchaseModel
import com.dwmaia.mercadolivro.repository.PurcharseRepository
import org.springframework.stereotype.Service

@Service
class PurchaseService(private val purcharseRepository:PurcharseRepository) {

    fun create(purchaseModel: PurchaseModel):PurchaseModel{
        return purcharseRepository.save(purchaseModel);
    }

}
