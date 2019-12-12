package com.creditas.backendphones.product.domain.dao

import com.creditas.backendphones.product.domain.entities.ProductStock
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface IProductStockDao : CrudRepository<ProductStock, Int> {

    fun findAll(pageable: Pageable): MutableList<ProductStock>

    @Query("SELECT * FROM product_stock", nativeQuery = true)
    fun getAllSqlNative(): MutableList<ProductStock>


    @Query("SELECT * FROM creditasbackendbdv2.product_stock WHERE model_id LIKE 8 ORDER BY price LIMIT 1", nativeQuery = true)
    fun getCheapestModel(): MutableList<ProductStock>


    @Query("SELECT * FROM (select id, model_id, min(price) as minprice from creditasbackendbdv2.product_stock where stock>0 group by model_id) as x inner join creditasbackendbdv2.product_stock as p on p.id = x.id",
            nativeQuery = true)
    fun getAllCheapestModelsWithStock(): MutableList<ProductStock>

    @Query("SELECT * FROM (select id, model_id, min(price) as minprice from creditasbackendbdv2.product_stock where stock>0 group by model_id) as x inner join creditasbackendbdv2.product_stock as p on p.id = x.id",
            countQuery = "SELECT count(*) FROM (select id, model_id, min(price) as minprice from creditasbackendbdv2.product_stock where stock>0 group by model_id ) as x inner join creditasbackendbdv2.product_stock as p on p.id = x.id",
            nativeQuery = true)
    fun getAllCheapestModelsWithStockPaged(pageable: Pageable): Page<ProductStock>


}