package com.creditas.backendphones.product.services

import com.creditas.backendphones.product.domain.entities.ProductStock
import org.springframework.data.domain.Page
import java.util.*

interface IProductService {

    fun getAllCheapestModelsWithStock(): MutableList<ProductStock>
    fun getAllCheapestModelsWithStockPaged(page: Int): Page<ProductStock>
    fun getAllProductsOfThisModelWithStockOrderedByPrice(model: Int): MutableList<ProductStock>
    fun getStockProductById(id:Int): ProductStock
    fun saveStockProduct(stock: ProductStock)
    fun productsFilteredByKeywords(stringFilter: String):MutableList<ProductStock>
    fun setBdPhonesExample()

}