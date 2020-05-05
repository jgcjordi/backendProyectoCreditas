package com.creditas.backendphones.orders.services


import com.creditas.backendphones.orders.domain.dao.IInvoiceDao
import com.creditas.backendphones.orders.domain.dao.IOrderItemDao
import com.creditas.backendphones.orders.domain.entities.Invoice
import com.creditas.backendphones.orders.domain.entities.OrderItem
import com.creditas.backendphones.product.domain.entities.ProductStock
import com.creditas.backendphones.user.domain.entities.ShopUser
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service


@Service
class OrderServiceImpl : IOrderService {

    @Autowired
    private lateinit var invoiceDao: IInvoiceDao
    @Autowired
    private lateinit var orderItemDao: IOrderItemDao

    private val LOGGER = LogFactory.getLog("OrderServiceImpl.class")

    override fun purchaseProduct(shopUser: ShopUser, productStock: ProductStock): Boolean {
        val invoice = invoiceDao.save(Invoice(null, shopUser))
        orderItemDao.save(OrderItem(null, productStock, invoice))
        return true
    }

    override fun getLastInvoiceOfUser(shopUser: ShopUser): Invoice {
        return invoiceDao.findLastProductPurchasedByThisUserId(shopUser.id!!)
    }

    override fun hasLastInvoiceOfUser(shopUser: ShopUser): Boolean {
        return try {
            invoiceDao.findLastProductPurchasedByThisUserId(shopUser.id!!)
            true
        }catch (e: EmptyResultDataAccessException){
            false
        }
    }


}