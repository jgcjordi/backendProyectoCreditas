package com.creditas.backendphones.user.infraestructure.security

import io.jsonwebtoken.Claims
import org.apache.juli.logging.LogFactory
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthorizationFilter: OncePerRequestFilter() {

    val LOGGER = LogFactory.getLog("JWTAuthorizationFilter.class")

    val header:String="Authorization"
    val prefix:String ="Bearer"

    lateinit var properties:Properties

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {

//        if(this.existJWT(request,response)){
//            var claims: Claims = this.validateJWT(request)
//            if(claims.get("authorities")!=null){
//                this.setUpSpringAuthentication(claims)
//            }else{
//                SecurityContextHolder.clearContext()
//            }
//
//        }
        if(this.existJWT(request)){

        }
        filterChain.doFilter(request, response)
    }

    fun existJWT(req: HttpServletRequest):Boolean {
        val authHeader:String = req.getHeader(header)
        LOGGER.warn(authHeader)

        return true
    }



}