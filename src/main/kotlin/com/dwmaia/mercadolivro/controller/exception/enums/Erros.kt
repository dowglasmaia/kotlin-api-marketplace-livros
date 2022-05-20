package com.dwmaia.mercadolivro.controller.exception.enums

enum class Erros(val statusCode:String, val message: String) {
    ML1001 ("ML_1001","Book [%s] not existe"),
    ML1002 ("ML_1002","Fails to try to save the Book"),
    ML1003 ("ML_1003","Fails to try to updated the Customer"),
    ML1004 ("ML_1004","you cannot update a book with status [%s]"),

    ML2004 ("ML_2004","Customer [%s] not existe"),
    ML2005 ("ML_2005","Fails to try to save the Customer"),
    ML2006 ("ML_2006","Fails to try to updated the Customer")

}
