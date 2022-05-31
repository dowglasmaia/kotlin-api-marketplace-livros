package com.dwmaia.mercadolivro.validation.impl

import com.dwmaia.mercadolivro.service.CustomerService
import com.dwmaia.mercadolivro.validation.annotation.EmailAvaliable
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class EmailAvaliableValidation(
        private val customerService: CustomerService
) : ConstraintValidator<EmailAvaliable, String> {

    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        if (value.isNullOrEmpty()) {
            return false
        }
        return customerService.emailAvaliable(value);
    }
}