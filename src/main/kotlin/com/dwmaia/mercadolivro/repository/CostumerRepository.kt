package com.dwmaia.mercadolivro.repository

import com.dwmaia.mercadolivro.model.CustomerModel
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CostumerRepository : CrudRepository<CustomerModel,Int>  {

    fun findByNameContaining(name:String):List<CustomerModel>

}