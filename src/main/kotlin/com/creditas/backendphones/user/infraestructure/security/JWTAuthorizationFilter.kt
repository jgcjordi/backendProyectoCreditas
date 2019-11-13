package com.creditas.backendphones.user.infraestructure.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.apache.juli.logging.LogFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import java.util.*
import java.util.stream.Collectors
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthorizationFilter : OncePerRequestFilter() {

    val LOGGER = LogFactory.getLog("JWTAuthorizationFilter.class")

    val header: String = "Authorization"
    val prefix: String = "Bearer"

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {

        val authHeader: String = request.getHeader(header)
        LOGGER.warn(authHeader)
        val claims: Claims = validateJWT(request)
        if (claims.get("authorities") != null) {
            this.setUpSpringAuthentication(claims)
        } else {
            SecurityContextHolder.clearContext()
        }
        filterChain.doFilter(request, response)


    }

    private fun validateJWT(req: HttpServletRequest): Claims {
        val jwtToken: String = req.getHeader(header).replace(prefix, " ")
        return Jwts.parser().setSigningKey("keybackendcreditas".toByteArray())
                .parseClaimsJws(jwtToken).body
    }

    fun setUpSpringAuthentication(claims:Claims){
        val authorities = claims.get("authorities") as List<String>
        val auth = UsernamePasswordAuthenticationToken(claims.subject,
                        null, authorities.stream().map(::SimpleGrantedAuthority)
                                .collect(Collectors.toList<SimpleGrantedAuthority>()))
        SecurityContextHolder.getContext().authentication=auth
    }

}