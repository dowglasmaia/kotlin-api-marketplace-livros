package com.dwmaia.mercadolivro.extension

import com.dwmaia.mercadolivro.controller.request.PostCustomerDTO
import com.dwmaia.mercadolivro.controller.request.PutCustomerDTO
import com.dwmaia.mercadolivro.model.CustomerModel

/* Class Aux para criar Mapper de Objetos*/

fun PostCustomerDTO.toCustomerModel(): CustomerModel {
    return CustomerModel(name = name, email = email)
}

fun PutCustomerDTO.toCustomerModel(): CustomerModel {
    return CustomerModel( email = email)
}