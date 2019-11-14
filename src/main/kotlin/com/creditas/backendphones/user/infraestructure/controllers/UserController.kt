package com.creditas.backendphones.user.infraestructure.controllers


import com.creditas.backendphones.user.domain.entities.User
import com.creditas.backendphones.user.services.IUserService
import org.apache.juli.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("api/v1/user")
class UserController {

    private val LOGGER = LogFactory.getLog("UserController.class")


    @Autowired
    private lateinit var userService: IUserService


    //http://localhost:8080/api/v1/user/login
    @CrossOrigin(origins = ["http://localhost:3000"])
    @PostMapping("/login")
    fun login(@RequestBody user:User, request: HttpServletRequest): ResponseEntity<User> {
        if (userService.ifUserExist(user.email!!, user.password!!)) {
            val userBD = userService.getUserByEmail(user.email)
            val token:String = userService.getJWTToken(user.email, request)
            userBD.password = token
            return ResponseEntity(userBD, HttpStatus.OK)
        } else {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }

    //http://localhost:8080/api/v1/user/logged/purchase
    @CrossOrigin(origins = ["http://localhost:3000"])
    @PostMapping("/logged/purchase")
    fun purchasePhone(@RequestBody user:User):ResponseEntity<User>{
        return ResponseEntity(userService.purchasePhone(user.id_user!!, user.idLastPhonePurchased!!,
                user.idLastPhonePurchasedVersion!!, user.idLastPhonePurchasedColor!!), HttpStatus.OK)
    }


    //http://localhost:8080/api/v1/user/exampledata
    @GetMapping("/exampledata")
    fun setUsers(): ResponseEntity<Unit> {
        LOGGER.warn("Begin User Exampledata")
        userService.setUsersExample()
        return ResponseEntity(HttpStatus.OK)
    }
}