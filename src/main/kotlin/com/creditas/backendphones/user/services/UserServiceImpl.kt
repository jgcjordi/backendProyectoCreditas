package com.creditas.backendphones.user.services

import com.creditas.backendphones.user.domain.entities.ShopUser
import com.creditas.backendphones.user.domain.dao.IUserDao
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils.commaSeparatedStringToAuthorityList
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors
import javax.servlet.http.HttpServletRequest



@Service
class UserServiceImpl : IUserService {

    @Value("\${app.keyGenerateToken}")
    lateinit var keyGenerateToken:String

    @Autowired
    private lateinit var userDao: IUserDao

    private val LOGGER = LogFactory.getLog("UserServiceImpl.class")

    override fun ifUserExist(email:String, password:String): Boolean {
        if(userDao.existsByEmail(email)){
            val user = userDao.findByEmail(email)
            LOGGER.warn(user.email.toString())
            return user.password == password
        }else{
            return false
        }
    }

    override fun ifEmailExist(email:String): Boolean {
        return userDao.existsByEmail(email)
    }

    override fun registryNewUser(shopUser:ShopUser): ShopUser {
        userDao.save(shopUser)
        return userDao.findByEmail(shopUser.email!!)
    }


    override fun getUserByEmail(email:String):ShopUser = userDao.findByEmail(email)

    override fun getUserById(id: Int): ShopUser = userDao.findById(id).get()

    override fun getJWTToken(email: String, request: HttpServletRequest): String {
        val grantedAuthorities:List<GrantedAuthority> = commaSeparatedStringToAuthorityList("ROLE_USER")

        val  token:String = Jwts
                .builder()
                .claim("ip", request.remoteAddr)
                .setId("Creditas")
                .setSubject(email)
                .claim("authorities", grantedAuthorities.stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .setIssuedAt(Date(System.currentTimeMillis()))
                .setExpiration(Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512, keyGenerateToken.toByteArray()).compact()

        LOGGER.warn(keyGenerateToken)


        return "Bearer $token"
    }


    private fun getEmailUserFromBearer(bearer:String): String {
        val jwtToken: String = bearer.replace("Bearer", " ")
        return Jwts.parser().setSigningKey(keyGenerateToken.toByteArray())
                .parseClaimsJws(jwtToken).body.subject as String
    }

    override fun getUserFromBearer(bearer: String): ShopUser {
        return getUserByEmail(getEmailUserFromBearer(bearer))
    }


    override fun setBdUsersExample() {
        val user1 = ShopUser(null, "jgc.jordi@gmail.com", "Jordi Gomis", "password1")
        userDao.save(user1)
        val user2 = ShopUser(null, "kike@gmail.com", "Kike Gomis", "password2")
        userDao.save(user2)
        val user3 = ShopUser(null, "neus@gmail.com", "Neus Gomis", "password3")
        userDao.save(user3)
    }


}