package com.creditas.backendphones.product.infraestructure.controllers

import com.creditas.backendphones.product.domain.entities.ProductStock
import com.creditas.backendphones.product.services.IProductsService
import org.apache.juli.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@CrossOrigin
@RequestMapping("api/v1/products")
class ProductsController {

    private val LOGGER = LogFactory.getLog("PhoneController.class")

    @Autowired
    private lateinit var productsService: IProductsService

    //http://localhost:8080/api/v1/products/all
    @GetMapping("/all")
    fun getProducts():ResponseEntity<MutableList<ProductStock>>{
        val list:MutableList<ProductStock> = productsService.getAllStockProducts()
        return ResponseEntity(list, HttpStatus.OK)
    }

    //http://localhost:8080/api/v1/products/allCheapestModelsWithStockPaged
    @GetMapping("/allCheapestModelsWithStockPaged")
    fun getCheapestModels():ResponseEntity<Page<ProductStock>>{
        val list= productsService.getAllCheapestModelsWithStockPaged(0)
        return ResponseEntity(list, HttpStatus.OK)
    }

    //http://localhost:8080/api/v1/phone/allPaginated/2
    @GetMapping("/allPaginated/{page}")
    fun getPhonesPaginated(@PathVariable page: Int):ResponseEntity<MutableMap<String, Any>>{
        return ResponseEntity(productsService.getAllPhonesPaginated(page), HttpStatus.OK)
    }

    //http://localhost:8080/api/v1/phone/4
    @GetMapping("/{id}")
    fun getPhoneById(@PathVariable id: Int): Optional<ProductStock> = productsService.getPhoneById(id)

    //http://localhost:8080/api/v1/phone/phones?search=Motorola+Iphone
    @GetMapping("/phones")
    fun getPhonesFilteredByKeywords(@RequestParam(value = "search", defaultValue = "") string: String): MutableList<ProductStock> {
        LOGGER.info(string)
        return productsService.phonesFilteredByKeywords(string)
    }



    //http://localhost:8080/api/v1/products/exampledata
    @GetMapping("/exampledata")
    fun setPhones():ResponseEntity<Unit>{
        LOGGER.warn("Begin Phones exampledata")
        productsService.setBdPhonesExample()
        return ResponseEntity(HttpStatus.OK)
    }
}