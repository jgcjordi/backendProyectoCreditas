package com.creditas.backendphones.product.domain.dao

import com.creditas.backendphones.product.domain.entities.ProductStock
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface IProductStockDao : CrudRepository<ProductStock, Int> {

    @Query("SELECT * FROM (select model_id, min(price) as minprice from creditasbackendbdv2.product_stock where stock>0 group by model_id) as x inner join creditasbackendbdv2.product_stock as p on p.price = x.minprice",
            nativeQuery = true)
    fun findAllCheapestModelsWithStock(): MutableList<ProductStock>

    @Query("SELECT * FROM (select model_id, min(price) as minprice from creditasbackendbdv2.product_stock where stock>0 group by model_id) as x inner join creditasbackendbdv2.product_stock as p on p.price = x.minprice",
            countQuery = "SELECT count(*) FROM (select model_id, min(price) as minprice from creditasbackendbdv2.product_stock where stock>0 group by model_id) as x inner join creditasbackendbdv2.product_stock as p on p.price = x.minprice",
            nativeQuery = true)
    fun findAllCheapestModelsWithStockPaged(pageable: Pageable): Page<ProductStock>

    @Query("SELECT * FROM creditasbackendbdv2.product_stock where stock>0 and model_id = ?1 order by price",
            nativeQuery = true)
    fun findAllProductsOfThisModelWithStockOrderedByPrice(model: Int): MutableList<ProductStock>


}