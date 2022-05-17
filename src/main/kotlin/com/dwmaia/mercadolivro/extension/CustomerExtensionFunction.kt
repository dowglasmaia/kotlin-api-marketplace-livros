package com.dwmaia.mercadolivro.extension

import com.dwmaia.mercadolivro.controller.request.customer.PostCustomerDTO
import com.dwmaia.mercadolivro.controller.request.customer.PutCustomerDTO
import com.dwmaia.mercadolivro.model.CustomerModel

/* Class Aux para criar Mapper de Objetos*/

fun PostCustomerDTO.toCustomerModel(): CustomerModel {
    return CustomerModel(
            name = name,
            email = email,
            status = "ATIVO"
    )
}

fun PutCustomerDTO.toCustomerModel(customer: CustomerModel): CustomerModel {
    return CustomerModel(
            id = customer.id,
            name = this.name,
            email = this.email,
            status = customer.status
    )
}