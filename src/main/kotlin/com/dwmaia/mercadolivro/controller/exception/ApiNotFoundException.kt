package com.dwmaia.mercadolivro.controller.exception

class ApiNotFoundException(override val message : String, val errorCode: String  ) : Exception() {

}