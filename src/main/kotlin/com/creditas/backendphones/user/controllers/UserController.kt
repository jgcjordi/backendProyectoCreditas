package com.creditas.backendphones.user.controllers


import com.creditas.backendphones.user.services.IUserService
import org.apache.juli.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/user")
class UserController {

    private val LOGGER = LogFactory.getLog("UserController.class")

    @Autowired
    private lateinit var userService: IUserService

    //http://localhost:8080/api/v1/user/jgc.jordi@gmail.com/password1
    @CrossOrigin(origins = ["http://localhost:3000"])
    @GetMapping("/{email}/{password}")
    fun ifUserExist(@PathVariable email: String, @PathVariable password: String): Boolean {
        return userService.ifUserExist(email, password)
    }


    //http://localhost:8080/api/v1/user/exampledata
    @GetMapping("/exampledata")
    fun setUsers():ResponseEntity<Unit>{
        LOGGER.warn("Begin User Exampledata")
        userService.setUsersExample()
        return ResponseEntity(HttpStatus.OK)
    }
}