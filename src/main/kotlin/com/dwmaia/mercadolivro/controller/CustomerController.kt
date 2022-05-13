package com.dwmaia.mercadolivro.controller

import com.dwmaia.mercadolivro.controller.request.PostCustomerDTO
import com.dwmaia.mercadolivro.controller.request.PutCustomerDTO
import com.dwmaia.mercadolivro.extension.toCustomerModel
import com.dwmaia.mercadolivro.model.CustomerModel
import com.dwmaia.mercadolivro.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("custumers")
class CustomerController(  val customerService: CustomerService ) {

    @PostMapping()
    fun create(@RequestBody request: PostCustomerDTO): ResponseEntity<Void> {
        customerService.create(request.toCustomerModel())
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping()
    fun getAll(@RequestParam name: String?): ResponseEntity<List<CustomerModel>> {
        val resopnse = customerService.getAll(name)
        return ResponseEntity.status(HttpStatus.OK).body(resopnse);
    }

    @GetMapping("/{id}")
    fun getCustomer(@PathVariable id: Int): ResponseEntity<CustomerModel> {
        val response = customerService.getCustomer(id)
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody request: PutCustomerDTO): ResponseEntity<Void> {
        customerService.update(request.toCustomerModel(id));
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build()
    }


    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<Void> {
        customerService.delete(id)
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build()
    }

}