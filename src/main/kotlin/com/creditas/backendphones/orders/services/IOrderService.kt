package com.creditas.backendphones.orders.services

import com.creditas.backendphones.orders.domain.entities.Invoice
import com.creditas.backendphones.product.domain.entities.ProductStock
import com.creditas.backendphones.user.domain.entities.User
import javax.servlet.http.HttpServletRequest

interface IOrderService {

    fun purchaseProduct(user: User, productStock: ProductStock):Boolean
    fun getLastInvoiceOfUser(user:User):Invoice
    fun hasLastInvoiceOfUser(user:User):Boolean


}