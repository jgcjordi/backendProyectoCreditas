package com.creditas.backendphones.user.services

import com.creditas.backendphones.user.domain.entities.User
import com.creditas.backendphones.user.domain.dao.IUserDao
import org.apache.juli.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class UserServiceImpl : IUserService {

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

    override fun getUserByEmail(email:String):User = userDao.findByEmail(email)


    override fun setUsersExample() {
        val user1 = User(null, "jgc.jordi@gmail.com", "Jordi Gomis", "password1", -1, -1, -1)
        userDao.save(user1)
        val user2 = User(null, "kike@gmail.com", "Kike Gomis", "password2", -1, -1, -1)
        userDao.save(user2)
        val user3 = User(null, "neus@gmail.com", "Neus Gomis", "password3", -1, -1, -1)
        userDao.save(user3)
    }


}