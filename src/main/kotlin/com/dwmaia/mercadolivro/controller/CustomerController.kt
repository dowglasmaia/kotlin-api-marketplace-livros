package com.dwmaia.mercadolivro.controller

import com.dwmaia.mercadolivro.controller.request.PostCustomerDTO
import com.dwmaia.mercadolivro.model.CustomerModel
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("custumers")
class CustomerController {

    val customers = mutableListOf<CustomerModel>()

    @GetMapping()
    fun getCustomer(): ResponseEntity<List<CustomerModel>> {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customers)
    }

    @PostMapping()
    fun create(@RequestBody request: PostCustomerDTO): ResponseEntity<Void> {
        println(request)

        var id = generationId()

        customers.add(CustomerModel(id, request.name, request.email))

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    private fun generationId(): String {
        var id = if (customers.isEmpty()) {
            1
        } else {
            customers.last().id.toInt() + 1
        }
        return id.toString()
    }


}