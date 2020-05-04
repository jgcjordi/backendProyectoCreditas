package com.creditas.backendphones

import com.creditas.backendphones.product.services.ProductServiceImpl
import com.creditas.backendphones.user.services.UserServiceImpl
import org.hibernate.bytecode.BytecodeLogger.LOGGER
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class OnBoot(private val productServiceImpl: ProductServiceImpl, private val userServiceImpl: UserServiceImpl) : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        LOGGER.warn("Begin Phones exampledata")
        productServiceImpl.setBdPhonesExample()
        userServiceImpl.setBdUsersExample()
    }
}