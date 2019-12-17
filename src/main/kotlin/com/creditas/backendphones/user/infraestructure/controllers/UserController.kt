package com.creditas.backendphones.user.infraestructure.controllers


import com.creditas.backendphones.orders.services.IOrderService
import com.creditas.backendphones.product.domain.entities.ProductStock
import com.creditas.backendphones.user.domain.entities.User
import com.creditas.backendphones.user.services.IUserService
import org.apache.juli.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
@CrossOrigin
@RequestMapping("api/v1/user")
class UserController {

    private val LOGGER = LogFactory.getLog("UserController.class")

    @Autowired
    private lateinit var userService: IUserService
    @Autowired
    private lateinit var orderService: IOrderService

    //http://localhost:8080/api/v1/user/login
    @PostMapping("/login")
    fun login(@RequestBody user:User, request: HttpServletRequest): ResponseEntity<User> {
        return if (userService.ifUserExist(user.email!!, user.password!!)) {
            val userBD = userService.getUserByEmail(user.email)
            val token:String = userService.getJWTToken(user.email, request)
            userBD.password = token
            ResponseEntity(userBD, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    //http://localhost:8080/api/v1/user/singIn
    @PostMapping("/singIn")
    fun singIn(@RequestBody user:User, request: HttpServletRequest): ResponseEntity<User> {
        return if (!userService.ifEmailExist(user.email!!)) {
            val userBD = userService.registryNewUser(user)
            val token:String = userService.getJWTToken(userBD.email!!, request)
            userBD.password = token
            ResponseEntity(userBD, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.PRECONDITION_FAILED)
        }
    }

    //http://localhost:8080/api/v1/user/logged/validToken
    @GetMapping("/logged/validToken")
    fun isValidToken():ResponseEntity<Boolean>{
        return ResponseEntity(true, HttpStatus.OK)
    }

    //http://localhost:8080/api/v1/user/logged/hasLastPurchase
    @GetMapping("/logged/hasLastPurchase")
    fun hasLastPurchase(@RequestHeader(value = "Authorization", defaultValue = "") bearer: String):ResponseEntity<Boolean>{
        val invoice = orderService.getLastInvoiceOfUser(userService.getUserFromBearer(bearer))
        return ResponseEntity(true, HttpStatus.OK)
    }

    //http://localhost:8080/api/v1/user/logged/getLastPurchase
    @GetMapping("/logged/getLastPurchase")
    fun getLastPurchase(@RequestHeader(value = "Authorization", defaultValue = "") bearer: String):ResponseEntity<ProductStock>{
        LOGGER.warn(bearer)
        return if(orderService.hasLastInvoiceOfUser(userService.getUserFromBearer(bearer))){
            val product = orderService.getLastInvoiceOfUser(userService.getUserFromBearer(bearer)).order_items[0].order_item
            ResponseEntity(product, HttpStatus.OK)
        }else{
            ResponseEntity(HttpStatus.NO_CONTENT)
        }


    }
}