package com.dwmaia.mercadolivro.extension

import com.dwmaia.mercadolivro.controller.request.customer.PostCustomerDTO
import com.dwmaia.mercadolivro.controller.request.customer.PutCustomerDTO
import com.dwmaia.mercadolivro.controller.response.customer.CustomerResponse
import com.dwmaia.mercadolivro.model.CustomerModel
import com.dwmaia.mercadolivro.model.enums.CustomerStatus

/* Class Aux para criar Mapper de Objetos*/

fun PostCustomerDTO.toCustomerModel(): CustomerModel {
    return CustomerModel(
            name = name,
            email = email,
            status = CustomerStatus.ATIVO,
            password = this.password
    )
}

fun PutCustomerDTO.toCustomerModel(customer: CustomerModel): CustomerModel {
    return CustomerModel(
            id = customer.id,
            name = this.name,
            email = this.email,
            status = customer.status,
            password = customer.password
    )
}

fun CustomerModel.toResponse(): CustomerResponse {
    return CustomerResponse(
            id = this.id!!,
            name = this.name!!,
            email = this.email,
            status = this.status
    )
}