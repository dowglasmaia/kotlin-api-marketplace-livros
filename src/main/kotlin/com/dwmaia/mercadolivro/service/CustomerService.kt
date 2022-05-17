package com.dwmaia.mercadolivro.service


import com.dwmaia.mercadolivro.model.CustomerModel
import com.dwmaia.mercadolivro.repository.CostumerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(
        val repository: CostumerRepository,
        val bookService: BookService
) {

    fun getAll(name: String?): List<CustomerModel> {
        name?.let {
            return repository.findByNameContaining(name)
        }
        return repository.findAll().toList()
    }

    fun findById(id: Int): CustomerModel {
        return repository.findById(id).orElseThrow();
    }

    fun update(request: CustomerModel) {
        if (!repository.existsById(request.id!!)) {
            throw Exception()
        }
        repository.save(request)
    }

    fun create(request: CustomerModel): CustomerModel{
        try {
            return repository.save(request)
        } catch (e: Exception) {
            throw RuntimeException()
        }
    }

    fun delete(id: Int) {
        val customer = findById(id)
        bookService.deleteByCustomer(customer)
        customer.status = "INATIVO"

        update(customer)
    }

}