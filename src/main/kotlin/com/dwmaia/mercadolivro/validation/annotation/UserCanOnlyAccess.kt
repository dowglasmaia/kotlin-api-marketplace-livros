package com.dwmaia.mercadolivro.validation.annotation

import org.springframework.security.access.prepost.PreAuthorize

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@PreAuthorize("hasRole('ROLE_ADMIN') || #id == authentication.principal.id") // o ID so tem acesso a dados do seu propio ID.
annotation class UserCanOnlyAccess
