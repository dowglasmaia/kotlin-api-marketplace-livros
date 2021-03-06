package com.dwmaia.mercadolivro.security

import com.dwmaia.mercadolivro.exception.AuthenticationException
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.Date

@Component
class JwtUtil {

    @Value("\${jwt.expiration}")
    private val expiration: Long? = null

    @Value("\${jwt.secret}")
    private val secret: String? = null

    fun generateToken(id: Int): String {
        return Jwts.builder()
                .setSubject(id.toString())
                .setExpiration(Date(System.currentTimeMillis() + expiration!!))
                .signWith(SignatureAlgorithm.HS512, secret!!.toByteArray())
                .compact()
    }

    fun isValidToken(token: String): Boolean {
        val claims = getClains(token)
        if (Date().after(claims.expiration) || claims.expiration == null || claims.subject == null) {
            return false
        }
        return true
    }

    private fun getClains(token: String): Claims {
        try {
            return Jwts.parser().setSigningKey(secret!!.toByteArray())
                    .parseClaimsJws(token).body
        } catch (ex: Exception) {
            throw AuthenticationException("Token Inválido", "999")
        }
    }

    fun getSubject(token: String): String {
        return getClains(token).subject
    }

}