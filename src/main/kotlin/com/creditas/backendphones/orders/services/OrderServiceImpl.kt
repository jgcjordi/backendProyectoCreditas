package com.creditas.backendphones.orders.services


import com.creditas.backendphones.orders.domain.dao.IInvoiceDao
import com.creditas.backendphones.orders.domain.dao.IOrderItemDao
import com.creditas.backendphones.orders.domain.entities.Invoice
import com.creditas.backendphones.orders.domain.entities.OrderItem
import com.creditas.backendphones.product.domain.dao.IProductStockDao
import com.creditas.backendphones.product.domain.entities.ProductStock
import com.creditas.backendphones.user.domain.dao.IUserDao
import com.creditas.backendphones.user.domain.entities.User
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

    override fun purchaseProduct(user: User, productStock: ProductStock): Boolean {
        val invoice = invoiceDao.save(Invoice(null, user))
        orderItemDao.save(OrderItem(null, productStock, invoice))
        return true
    }

    override fun getLastInvoiceOfUser(user: User): Invoice {
        return invoiceDao.findLastProductPurchasedByThisUserId(user.id!!)
    }

    override fun hasLastInvoiceOfUser(user: User): Boolean {
        return try {
            invoiceDao.findLastProductPurchasedByThisUserId(user.id!!)
            true
        }catch (e: EmptyResultDataAccessException){
            false
        }
    }


}