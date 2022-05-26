package com.dwmaia.mercadolivro.controller

import com.dwmaia.mercadolivro.controller.mapper.PurchaseMapper
import com.dwmaia.mercadolivro.controller.request.purchase.PostPurchaseRequest
import com.dwmaia.mercadolivro.service.PurchaseService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI

@RestController("/purchases")
class PurchaseController(
        private val purchaseService: PurchaseService,
        private val purchaseMapper: PurchaseMapper
) {

    @PostMapping
    fun purchase(@RequestBody request: PostPurchaseRequest):ResponseEntity<Void> {
        val purchase = purchaseMapper.toModel(request)
        val purchaseSavad = purchaseService.create(purchase)

        val uri: URI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(purchaseSavad.id).toUri();
        return ResponseEntity.created(uri).build();
    }
}