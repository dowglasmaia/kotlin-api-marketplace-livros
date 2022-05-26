package com.dwmaia.mercadolivro.repository

import com.dwmaia.mercadolivro.model.PurchaseModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PurcharseRepository : JpaRepository<PurchaseModel, Int> {


}