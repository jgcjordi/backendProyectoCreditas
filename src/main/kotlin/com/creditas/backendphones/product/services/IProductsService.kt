package com.creditas.backendphones.product.services

import com.creditas.backendphones.product.domain.entities.ProductStock
import org.springframework.data.domain.Page
import java.util.*

interface IProductsService {

    fun getAllStockProducts():MutableList<ProductStock>
    fun getAllCheapestModelsWithStockPaged(page: Int): Page<ProductStock>
    fun getAllCheapestModels():MutableList<ProductStock>
    fun getPhoneById(id: Int): Optional<ProductStock>
    fun phonesFilteredByKeywords(stringFilter: String):MutableList<ProductStock>
    fun setBdPhonesExample()

    fun getAllPhonesPaginated(page:Int): MutableMap<String, Any>

}