package com.creditas.backendphones.product.infraestructure.controllers

import com.creditas.backendphones.product.domain.entities.ProductStock
import com.creditas.backendphones.product.services.IProductService
import com.creditas.backendphones.user.services.IUserService
import org.apache.juli.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
@RequestMapping("api/v1/products")
class ProductsController {

    private val LOGGER = LogFactory.getLog("PhoneController.class")

    @Autowired
    private lateinit var productService: IProductService
    @Autowired
    private lateinit var userService: IUserService


    @GetMapping
    fun isWorking() = "I am working"

    //http://localhost:8080/api/v1/products/allCheapestModelsWithStockPaged/0
    @GetMapping("/allCheapestModelsWithStockPaged/{page}")
    fun allCheapestModelsWithStockPaged(@PathVariable page: Int): ResponseEntity<Page<ProductStock>> {
        return ResponseEntity(productService.getAllCheapestModelsWithStockPaged(page), HttpStatus.OK)
    }

    //http://localhost:8080/api/v1/products/allProductsOfThisModelWithStockOrderedByPrice/13
    @GetMapping("/allProductsOfThisModelWithStockOrderedByPrice/{id}")
    fun getAllProductsOfThisModelWithStockOrderedByPrice(@PathVariable id: Int): ResponseEntity<MutableList<ProductStock>> {
        return ResponseEntity(productService.getAllProductsOfThisModelWithStockOrderedByPrice(id), HttpStatus.OK)
    }

    //http://localhost:8080/api/v1/phone/phones?search=Motorola+Iphone
    @GetMapping("/phones")
    fun getProductsFilteredByKeywords(@RequestParam(value = "search", defaultValue = "") string: String): MutableList<ProductStock> {
        LOGGER.warn(string)
        return productService.productsFilteredByKeywords(string)
    }
}