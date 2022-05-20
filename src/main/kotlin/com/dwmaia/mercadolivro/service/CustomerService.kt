package com.dwmaia.mercadolivro.service


import com.dwmaia.mercadolivro.controller.exception.ApiBadRequestException
import com.dwmaia.mercadolivro.controller.exception.ApiNotFoundException
import com.dwmaia.mercadolivro.controller.exception.enums.Erros
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
        return repository.findById(id).orElseThrow {
            ApiNotFoundException(Erros.ML2004.message.format(id), Erros.ML2004.statusCode)
        }
    }

    fun update(request: CustomerModel) {
        this.findById(request.id!!)

        repository.save(request)
    }

    fun create(request: CustomerModel): CustomerModel {
        try {
            return repository.save(request)
        } catch (e: Exception) {
            throw ApiBadRequestException(Erros.ML2005.message, Erros.ML2005.statusCode)
        }
    }

    fun delete(id: Int) {
        val customer = findById(id)
        bookService.deleteByCustomer(customer)
        customer.status = "INATIVO"

        update(customer)
    }

}