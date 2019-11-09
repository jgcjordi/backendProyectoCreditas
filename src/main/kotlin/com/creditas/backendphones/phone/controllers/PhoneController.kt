package com.creditas.backendphones.phone.controllers

import com.creditas.backendphones.phone.domain.entities.Phone
import com.creditas.backendphones.phone.services.IPhoneService
import org.apache.juli.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/phone")
class PhoneController {

    private val LOGGER = LogFactory.getLog("PhonesController.class")

    @Autowired
    private lateinit var phoneService: IPhoneService

    @GetMapping("/all")
    fun getPhones():ResponseEntity<MutableList<Phone>>{
        val list:MutableList<Phone> = phoneService.getPhones()
        return ResponseEntity(list, HttpStatus.OK)
    }

    @GetMapping("/exampledata")
    fun setPhones():ResponseEntity<Unit>{
        LOGGER.warn("Begin exampledata")
        phoneService.setPhonesExample()
        return ResponseEntity(HttpStatus.OK)
    }
}