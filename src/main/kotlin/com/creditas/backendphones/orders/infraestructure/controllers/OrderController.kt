package com.creditas.backendphones.orders.infraestructure.controllers


import com.creditas.backendphones.orders.domain.entities.UserProductIds
import com.creditas.backendphones.orders.services.IOrderService
import com.creditas.backendphones.product.domain.entities.ProductStock
import com.creditas.backendphones.product.services.IProductService
import com.creditas.backendphones.user.domain.entities.User
import com.creditas.backendphones.user.services.IUserService
import org.apache.juli.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
@RequestMapping("api/v1/user")
class OrderController {

    private val LOGGER = LogFactory.getLog("OrderController.class")

    @Autowired
    private lateinit var orderService: IOrderService
    @Autowired
    private lateinit var productService: IProductService
    @Autowired
    private lateinit var userService: IUserService


    //http://localhost:8080/api/v1/order/logged/purchase
    @PostMapping("/logged/purchase")
    fun purchasePhone(@RequestBody ids: UserProductIds): ResponseEntity<Boolean> {
        val stockProduct = productService.getStockProductById(ids.idStockProduct)
        val user = userService.getUserById(ids.idUser)
        if (orderService.purchaseProduct(user, stockProduct)) {
            stockProduct.stock -= 1
            stockProduct.sold_out += 1
            productService.saveStockProduct(stockProduct)
            return ResponseEntity(true, HttpStatus.OK)
        }
        return ResponseEntity(false, HttpStatus.PRECONDITION_FAILED)
    }
}