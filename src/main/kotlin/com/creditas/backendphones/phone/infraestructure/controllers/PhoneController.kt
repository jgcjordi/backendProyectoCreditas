package com.creditas.backendphones.phone.infraestructure.controllers

import com.creditas.backendphones.phone.domain.entities.Phone
import com.creditas.backendphones.phone.services.IPhoneService
import org.apache.juli.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("api/v1/phone")
class PhoneController {

    private val LOGGER = LogFactory.getLog("PhoneController.class")

    @Autowired
    private lateinit var phoneService: IPhoneService

    //http://localhost:8080/api/v1/phone/all
    @CrossOrigin(origins = ["http://localhost:3000"])
    @GetMapping("/all")
    fun getPhones():ResponseEntity<MutableList<Phone>>{
        val list:MutableList<Phone> = phoneService.getAllPhones()
        return ResponseEntity(list, HttpStatus.OK)
    }

    //http://localhost:8080/api/v1/phone/7
    @CrossOrigin(origins = ["http://localhost:3000"])
    @GetMapping("/{id}")
    fun getPhoneById(@PathVariable id: Int): Optional<Phone> = phoneService.getPhoneById(id)

    //http://localhost:8080/api/v1/phone/phones?search=Motorola+Iphone
    @CrossOrigin(origins = ["http://localhost:3000"])
    @GetMapping("/phones")
    fun getPhonesFilteredByKeywords(@RequestParam(value = "search", defaultValue = "") string: String): MutableList<Phone> {
        LOGGER.info(string)
        return phoneService.phonesFilteredByKeywords(string)
    }



    //http://localhost:8080/api/v1/phone/exampledata
    @GetMapping("/exampledata")
    fun setPhones():ResponseEntity<Unit>{
        LOGGER.warn("Begin Phones exampledata")
        phoneService.setPhonesExample()
        return ResponseEntity(HttpStatus.OK)
    }
}