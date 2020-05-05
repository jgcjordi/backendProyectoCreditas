package com.creditas.backendphones.orders.services

import com.creditas.backendphones.orders.domain.entities.Invoice
import com.creditas.backendphones.product.domain.entities.ProductStock
import com.creditas.backendphones.user.domain.entities.ShopUser

interface IOrderService {

    fun purchaseProduct(shopUser: ShopUser, productStock: ProductStock):Boolean
    fun getLastInvoiceOfUser(shopUser:ShopUser):Invoice
    fun hasLastInvoiceOfUser(shopUser:ShopUser):Boolean


}