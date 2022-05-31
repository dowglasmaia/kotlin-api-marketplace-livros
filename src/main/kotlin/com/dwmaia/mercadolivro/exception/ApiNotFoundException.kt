package com.dwmaia.mercadolivro.exception

class ApiNotFoundException(override val message : String, val errorCode: String  ) : Exception() {

}