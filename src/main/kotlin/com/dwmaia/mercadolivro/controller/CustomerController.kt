package com.dwmaia.mercadolivro.controller

import com.dwmaia.mercadolivro.controller.request.customer.PostCustomerDTO
import com.dwmaia.mercadolivro.controller.request.customer.PutCustomerDTO
import com.dwmaia.mercadolivro.extension.toCustomerModel
import com.dwmaia.mercadolivro.model.CustomerModel
import com.dwmaia.mercadolivro.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI

@RestController
@RequestMapping("custumers")
class CustomerController(val customerService: CustomerService) {

    @PostMapping()
    fun create(@RequestBody request: PostCustomerDTO): ResponseEntity<Void> {
        val customerSaved = customerService.create(request.toCustomerModel())

        val uri:URI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(customerSaved.id).toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping()
    fun getAll(@RequestParam name: String?): ResponseEntity<List<CustomerModel>> {
        val resopnse = customerService.getAll(name)
        return ResponseEntity.status(HttpStatus.OK).body(resopnse);
    }

    @GetMapping("/{id}")
    fun getCustomer(@PathVariable id: Int): ResponseEntity<CustomerModel> {
        val response = customerService.findById(id)
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody request: PutCustomerDTO): ResponseEntity<Void> {
        val customer = customerService.findById(id)
        customerService.update(request.toCustomerModel(customer));
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