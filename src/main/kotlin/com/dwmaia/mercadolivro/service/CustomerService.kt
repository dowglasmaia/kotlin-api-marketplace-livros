package com.dwmaia.mercadolivro.service


import com.dwmaia.mercadolivro.exception.ApiBadRequestException
import com.dwmaia.mercadolivro.exception.ApiNotFoundException
import com.dwmaia.mercadolivro.exception.enums.EnumErros
import com.dwmaia.mercadolivro.model.CustomerModel
import com.dwmaia.mercadolivro.model.enums.CustomerStatus
import com.dwmaia.mercadolivro.model.enums.Roles
import com.dwmaia.mercadolivro.repository.CostumerRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class CustomerService(
        private val repository: CostumerRepository,
        private val bookService: BookService,
        private val bCrypt: BCryptPasswordEncoder
) {

    fun create(request: CustomerModel): CustomerModel {
        try {
            val requestCopy = request.copy(
                    roles = setOf(Roles.CUSTOMER),
                    password = bCrypt.encode(request.password)
            )

            return repository.save(requestCopy)
        } catch (e: Exception) {
            throw ApiBadRequestException(EnumErros.ML2005.message, EnumErros.ML2005.statusCode)
        }
    }

    fun update(request: CustomerModel) {
        this.findById(request.id!!)

        repository.save(request)
    }

    fun getAll(name: String?): List<CustomerModel> {
        name?.let {
            return repository.findByNameContaining(name)
        }
        return repository.findAll().toList()
    }

    fun findById(id: Int): CustomerModel {
        return repository.findById(id).orElseThrow {
            ApiNotFoundException(EnumErros.ML2004.message.format(id), EnumErros.ML2004.statusCode)
        }
    }

    fun delete(id: Int) {
        val customer = findById(id)
        bookService.deleteByCustomer(customer)
        customer.status = CustomerStatus.INATIVO

        update(customer)
    }

    fun emailAvaliable(value: String): Boolean {
        return !repository.existsByEmail(value)
    }

    fun findByEmail(email: String):CustomerModel? {
        return repository.findByEmail(email)
    }

}