package com.dwmaia.mercadolivro.controller

import com.dwmaia.mercadolivro.controller.request.customer.PostCustomerDTO
import com.dwmaia.mercadolivro.controller.request.customer.PutCustomerDTO
import com.dwmaia.mercadolivro.controller.response.customer.CustomerResponse
import com.dwmaia.mercadolivro.extension.toCustomerModel
import com.dwmaia.mercadolivro.extension.toResponse
import com.dwmaia.mercadolivro.service.CustomerService
import com.dwmaia.mercadolivro.validation.annotation.UserCanOnlyAccess
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI
import javax.validation.Valid

@RestController
@RequestMapping("custumers")
class CustomerController(
        private val customerService: CustomerService
) {

    @PostMapping()
    fun create(@Valid @RequestBody request: PostCustomerDTO): ResponseEntity<Void> {
        val customerSaved = customerService.create(request.toCustomerModel())

        val uri: URI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(customerSaved.id).toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping()
    fun getAll(@RequestParam("name", required = false) name: String?): ResponseEntity<List<CustomerResponse>> {
        val resopnse = customerService.getAll(name).map { it.toResponse() }
        return ResponseEntity.status(HttpStatus.OK).body(resopnse);
    }

    @GetMapping("/{id}")
    @UserCanOnlyAccess
    fun getCustomer(@PathVariable id: Int): ResponseEntity<CustomerResponse> {
        val response = customerService.findById(id).toResponse()
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @Valid @RequestBody request: PutCustomerDTO): ResponseEntity<Void> {
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