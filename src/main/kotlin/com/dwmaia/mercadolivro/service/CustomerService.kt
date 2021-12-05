package com.dwmaia.mercadolivro.service

import com.dwmaia.mercadolivro.controller.request.PostCustomerDTO
import com.dwmaia.mercadolivro.controller.request.PutCustomerDTO
import com.dwmaia.mercadolivro.model.CustomerModel
import org.springframework.stereotype.Service

@Service
class CustomerService {
    val customers = mutableListOf<CustomerModel>()

    fun getAll(name: String?): List<CustomerModel> {
        name?.let {
            return customers.filter {
                it.name.contains(name, true)
            }
        }
        return customers
    }

    fun getCustomer(id: String): CustomerModel {
        return customers.filter { it.id == id }.first()
    }


    fun update(id: String, request: PutCustomerDTO) {
        customers.filter { it.id == id }.first().let {
            it.email = request.email
        }
    }

    fun create(request: PostCustomerDTO) {
        println(request)
        var id = generationId()
        customers.add(CustomerModel(id, request.name, request.email))
    }


    fun delete(id: String) {
        customers.removeIf { it.id == id };
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