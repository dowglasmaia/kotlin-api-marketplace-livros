package com.dwmaia.mercadolivro.service

import com.dwmaia.mercadolivro.controller.request.PostCustomerDTO
import com.dwmaia.mercadolivro.controller.request.PutCustomerDTO
import com.dwmaia.mercadolivro.model.CustomerModel
import com.dwmaia.mercadolivro.repository.CostumerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService ( val repository : CostumerRepository ) {
    val customers = mutableListOf<CustomerModel>()

    fun getAll(name: String?): List<CustomerModel> {
        name?.let {
            return customers.filter {
                it.name!!.contains(name, true)
            }
        }
        return customers
    }

    fun getCustomer(id: Int): CustomerModel {
        return repository.findById(id).orElseThrow();
    }


    fun update(id: Int, request: CustomerModel) {
        customers.filter { it.id == id }.first().let {
            it.email = request.email
        }
    }

    fun create(request: CustomerModel) {
    try {
      repository.save(request);
    }catch (e: Exception){
        throw java.lang.RuntimeException()
     }
    }


    fun delete(id: Int) {
        customers.removeIf { it.id == id };
    }


}