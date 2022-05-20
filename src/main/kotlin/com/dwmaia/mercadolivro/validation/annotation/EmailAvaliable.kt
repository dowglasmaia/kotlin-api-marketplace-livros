package com.dwmaia.mercadolivro.validation.annotation

import com.dwmaia.mercadolivro.validation.impl.EmailAvaliableValidation
import javax.validation.Constraint
import kotlin.reflect.KClass

@Constraint(validatedBy = [EmailAvaliableValidation::class])
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class EmailAvaliable(
        val message: String = "Email já cadastrado",
        val groups: Array<KClass<*>> = [],
        val payload: Array<KClass<*>> = []
)
