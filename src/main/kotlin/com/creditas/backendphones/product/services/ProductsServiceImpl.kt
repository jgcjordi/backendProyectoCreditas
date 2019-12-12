package com.creditas.backendphones.product.services

import com.creditas.backendphones.product.domain.dao.*
import com.creditas.backendphones.product.domain.entities.*
import org.apache.juli.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*


@Service
class ProductsServiceImpl : IProductsService {

    @Autowired
    private lateinit var productStockDao: IProductStockDao
    @Autowired
    private lateinit var brandDao: IBrandDao
    @Autowired
    private lateinit var colorDao: IColorDao
    @Autowired
    private lateinit var ramDao: IRamDao
    @Autowired
    private lateinit var storageDao: IStorageDao
    @Autowired
    private lateinit var modelDao: IModelDao

    private val LOGGER = LogFactory.getLog("PhoneServiceImpl.class")

    override fun getAllStockProducts(): MutableList<ProductStock> = productStockDao.findAll() as MutableList<ProductStock>

    override fun getAllCheapestModels(): MutableList<ProductStock>{
        val listModels = modelDao.findAll() as MutableList<Model>
        val listCheapestModels:MutableList<ProductStock>
        for (m in listModels){
            LOGGER.warn(m.name)
        }
        return productStockDao.getAllCheapestModelsWithStock()
    }

    override fun getAllCheapestModelsWithStockPaged(page: Int): Page<ProductStock> {
        val pageable: Pageable = PageRequest.of(page, 6);
        val x= productStockDao.getAllCheapestModelsWithStockPaged(pageable)
//        LOGGER.warn(x.content[0].price)
        return productStockDao.getAllCheapestModelsWithStockPaged(pageable)
    }

    override fun getAllPhonesPaginated(page: Int): MutableMap<String, Any> {
        val size = productStockDao.count()
        val pageSize = 6
        val pages = size / pageSize
        LOGGER.warn(pages.toString())
        val list = productStockDao.findAll(PageRequest.of(page, pageSize))
        return mutableMapOf("phoneList" to list, "totalPages" to pages, "currentPage" to page)
    }

    override fun getPhoneById(id: Int): Optional<ProductStock> = productStockDao.findById(id)

    override fun phonesFilteredByKeywords(stringFilter: String): MutableList<ProductStock> {
        val phones = productStockDao.findAll() as MutableList<ProductStock>
        val listKeywords: MutableList<String> = stringFilter.split(" ").toMutableList()
        for (i in listKeywords) listKeywords[listKeywords.indexOf(i)] = i.toLowerCase()

        val phonesFiltered = mutableListOf<ProductStock>()

        //Specific search with all keywords
        for (p in phones) {
            var add = true
            for (l in listKeywords) {
//                if (!p.brand!!.toLowerCase().contains(l) &&
//                        !p.model!!.toLowerCase().contains(l)) {
//                    add = false
//                }
            }
            if (add) phonesFiltered.add(p)
        }

        // If you don't find any results with the previous search, search with at least one word
        if (phonesFiltered.isEmpty()) {
            for (p in phones) {
                for (l in listKeywords) {
//                    if (p.brand!!.toLowerCase().contains(l) ||
//                            p.model!!.toLowerCase().contains(l)) {
//                        phonesFiltered.add(p)
//                        break
//                    }
                }
            }
        }
        return phonesFiltered
    }

    override fun setBdPhonesExample() {
        val brandGoogle = Brand(null, "Google", listOf())
        brandDao.save(brandGoogle)

        val ram4 = Ram(null, 4, listOf())
        ramDao.save(ram4)

        val storage64 = Storage(null, 64, listOf())
        storageDao.save(storage64)
        val storage128 = Storage(null, 128, listOf())
        storageDao.save(storage128)

        val colorBlack = Color(null, "Black", listOf())
        colorDao.save(colorBlack)
        val colorWhite = Color(null, "White", listOf())
        colorDao.save(colorWhite)
        val colorPink = Color(null, "Pink", listOf())
        colorDao.save(colorPink)


        val mPixel3 = Model(null, "Pixel 3",
                "Finding the best price for the Google Pixel 3 is no easy task. Here you will find where to buy the Google Pixel 3 at the best price. Prices are continuously tracked in over 140 stores so that you can find a reputable dealer with the best price",
                "https://cdn.phonehouse.es/res/product450/resource_1585992.jpg",
                listOf())
        modelDao.save(mPixel3)
        productStockDao.save(ProductStock(null, 5, 0, 504F, mPixel3, brandGoogle, colorBlack, ram4, storage64))
        productStockDao.save(ProductStock(null, 5, 0, 514F, mPixel3, brandGoogle, colorPink, ram4, storage64))
        productStockDao.save(ProductStock(null, 5, 0, 812F, mPixel3, brandGoogle, colorBlack, ram4, storage128))
        productStockDao.save(ProductStock(null, 5, 0, 822F, mPixel3, brandGoogle, colorPink, ram4, storage128))

        val mPixel3Xl = Model(null, "Pixel 3 XL",
                "Finding the best price for the Google Pixel 3 XL is no easy task. Here you will find where to buy the Google Pixel 3 XL at the best price. Prices are continuously tracked in over 140 stores so that you can find a reputable dealer with the best price",
                "https://cdn.phonehouse.es/res/product450/resource_1586585.jpg",
                listOf())
        modelDao.save(mPixel3Xl)
        productStockDao.save(ProductStock(null, 5, 0, 528F, mPixel3Xl, brandGoogle, colorBlack, ram4, storage64))
        productStockDao.save(ProductStock(null, 5, 0, 538F, mPixel3Xl, brandGoogle, colorWhite, ram4, storage64))
        productStockDao.save(ProductStock(null, 5, 0, 832F, mPixel3Xl, brandGoogle, colorBlack, ram4, storage128))
        productStockDao.save(ProductStock(null, 5, 0, 838F, mPixel3Xl, brandGoogle, colorWhite, ram4, storage128))
    }


//
//    override fun setPhonesExample() {
//        val phone1 = ProductStock(null, "Apple", "Iphone X", "https://cdn.phonehouse.es/res/product450/resource_350051.jpg", "Finding the best price for the Apple iPhone X is no easy task. Here you will find where to buy the Apple iPhone X at the best price. Prices are continuously tracked in over 140 stores so that you can find a reputable dealer with the best price", listOf(), listOf())
//        phoneDao.save(phone1)
//        val version1 = VersionPhone(null, 939F, 3, 64, phone1)
//        val version2 = VersionPhone(null, 1129F, 3, 265, phone1)
//        versionPhoneDao.save(version1)
//        versionPhoneDao.save(version2)
//        val color1 = Color(null, "Space Gray", phone1)
//        val color2 = Color(null, "Silver", phone1)
//        colorPhoneDao.save(color1)
//        colorPhoneDao.save(color2)
//
//
//        val phone2 = ProductStock(null, "Apple", "IPhone 11", "https://cdn-files.kimovil.com/phone_front/0003/47/thumb_246000_phone_front_big.jpeg", "Finding the best price for the Apple iPhone 11 is no easy task. Here you will find where to buy the Apple iPhone 11 at the best price. Prices are continuously tracked in over 140 stores so that you can find a reputable dealer with the best price", listOf(), listOf())
//        phoneDao.save(phone2)
//        val version3 = VersionPhone(null, 797F, 4, 64, phone2)
//        val version4 = VersionPhone(null, 826F, 4, 128, phone2)
//        val version5 = VersionPhone(null, 979F, 4, 256, phone2)
//        versionPhoneDao.save(version3)
//        versionPhoneDao.save(version4)
//        versionPhoneDao.save(version5)
//        val color3 = Color(null, "Black", phone2)
//        val color4 = Color(null, "Green", phone2)
//        val color5 = Color(null, "Purple", phone2)
//        val color6 = Color(null, "Red", phone2)
//        val color7 = Color(null, "White", phone2)
//        val color8 = Color(null, "Yellow", phone2)
//        colorPhoneDao.save(color3)
//        colorPhoneDao.save(color4)
//        colorPhoneDao.save(color5)
//        colorPhoneDao.save(color6)
//        colorPhoneDao.save(color7)
//        colorPhoneDao.save(color8)
//
//        val phone3 = ProductStock(null, "Samsung", "Galaxy S10", "https://cdn.phonehouse.es/res/product450/resource_1477411.jpg", "Finding the best price for the Samsung Galaxy S10 is no easy task. Here you will find where to buy the Samsung Galaxy S10 at the best price. Prices are continuously tracked in over 140 stores so that you can find a reputable dealer with the best price", listOf(), listOf())
//        phoneDao.save(phone3)
//        val version6 = VersionPhone(null, 850F, 8, 128, phone3)
//        val version7 = VersionPhone(null, 1260F, 8, 512, phone3)
//        versionPhoneDao.save(version6)
//        versionPhoneDao.save(version7)
//        val color9 = Color(null, "Prism black", phone3)
//        val color10 = Color(null, "Prism green", phone3)
//        val color11 = Color(null, "Prism white", phone3)
//        val color12 = Color(null, "Prism blue", phone3)
//        val color13 = Color(null, "Prism silver", phone3)
//        val color14 = Color(null, "Cardinal Red", phone3)
//        colorPhoneDao.save(color9)
//        colorPhoneDao.save(color10)
//        colorPhoneDao.save(color11)
//        colorPhoneDao.save(color12)
//        colorPhoneDao.save(color13)
//        colorPhoneDao.save(color14)
//
//        val phone4 = ProductStock(null, "Samsung", "Galaxy A70", "https://cdn.phonehouse.es/res/product450/resource_1891171.jpg", "Finding the best price for the Samsung Galaxy A70 is no easy task. Here you will find where to buy the Samsung Galaxy A70 at the best price. Prices are continuously tracked in over 140 stores so that you can find a reputable dealer with the best price", listOf(), listOf())
//        phoneDao.save(phone4)
//        val version8 = VersionPhone(null, 320F, 6, 128, phone4)
//        val version9 = VersionPhone(null, 360F, 8, 128, phone4)
//        versionPhoneDao.save(version8)
//        versionPhoneDao.save(version9)
//        val color15 = Color(null, "Black", phone4)
//        val color16 = Color(null, "Blue", phone4)
//        val color17 = Color(null, "White", phone4)
//        colorPhoneDao.save(color15)
//        colorPhoneDao.save(color16)
//        colorPhoneDao.save(color17)
//
//        val phone5 = ProductStock(null, "Alcatel", "1S", "https://cdn.phonehouse.es/res/product450/resource_1764921.jpg", "Finding the best price for the Alcatel 1S is no easy task. Here you will find where to buy the Alcatel 1S at the best price. Prices are continuously tracked in over 140 stores so that you can find a reputable dealer with the best price", listOf(), listOf())
//        phoneDao.save(phone5)
//        val version10 = VersionPhone(null, 86F, 3, 32, phone5)
//        val version11 = VersionPhone(null, 110F, 4, 64, phone5)
//        versionPhoneDao.save(version10)
//        versionPhoneDao.save(version11)
//        val color18 = Color(null, "Black", phone5)
//        val color19 = Color(null, "Blue", phone5)
//        colorPhoneDao.save(color18)
//        colorPhoneDao.save(color19)
//
//        val phone6 = ProductStock(null, "Alcatel", "3", "https://cdn.phonehouse.es/res/product450/resource_364555.jpg", "Finding the best price for the Alcatel 3 (2019) is no easy task. Here you will find where to buy the Alcatel 3 (2019) at the best price. Prices are continuously tracked in over 140 stores so that you can find a reputable dealer with the best price", listOf(), listOf())
//        phoneDao.save(phone6)
//        val version12 = VersionPhone(null, 138F, 3, 32, phone6)
//        val version13 = VersionPhone(null, 161F, 4, 64, phone6)
//        versionPhoneDao.save(version12)
//        versionPhoneDao.save(version13)
//        val color20 = Color(null, "Black", phone6)
//        val color21 = Color(null, "Blue", phone6)
//        colorPhoneDao.save(color20)
//        colorPhoneDao.save(color21)
//
//        val phone7 = ProductStock(null, "Motorola", "Moto G7 Plus", "https://cdn.phonehouse.es/res/viewtwo450/resource_1964334.jpg", "Finding the best price for the Motorola Moto G7 Plus is no easy task. Here you will find where to buy the Motorola Moto G7 Plus at the best price. Prices are continuously tracked in over 140 stores so that you can find a reputable dealer with the best price", listOf(), listOf())
//        phoneDao.save(phone7)
//        val version14 = VersionPhone(null, 216F, 4, 64, phone7)
//        versionPhoneDao.save(version14)
//        val color22 = Color(null, "Blue", phone7)
//        val color23 = Color(null, "Red", phone7)
//        colorPhoneDao.save(color22)
//        colorPhoneDao.save(color23)
//
//        val phone8 = ProductStock(null, "Motorola", "One Vision", "https://cdn.phonehouse.es/res/products-image/3/8/3/0/5/383058-2228084.jpg", "Finding the best price for the Motorola One Vision is no easy task. Here you will find where to buy the Motorola One Vision at the best price. Prices are continuously tracked in over 140 stores so that you can find a reputable dealer with the best price", listOf(), listOf())
//        phoneDao.save(phone8)
//        val version15 = VersionPhone(null, 199F, 4, 64, phone8)
//        versionPhoneDao.save(version15)
//        val color24 = Color(null, "Blue", phone8)
//        val color25 = Color(null, "Brown", phone8)
//        colorPhoneDao.save(color24)
//        colorPhoneDao.save(color25)
//
//        val phone9 = ProductStock(null, "Sharp", "Aquos C10", "https://cdn-files.kimovil.com/phone_front/0002/56/thumb_155997_phone_front_big.jpeg", "Finding the best price for the Sharp Aquos C10 is no easy task. Here you will find where to buy the Sharp Aquos C10 at the best price. Prices are continuously tracked in over 140 stores so that you can find a reputable dealer with the best price", listOf(), listOf())
//        phoneDao.save(phone9)
//        val version16 = VersionPhone(null, 100F, 4, 64, phone9)
//        versionPhoneDao.save(version16)
//        val color26 = Color(null, "Black", phone9)
//        val color27 = Color(null, "White", phone9)
//        colorPhoneDao.save(color26)
//        colorPhoneDao.save(color27)
//
//        val phone10 = ProductStock(null, "Sharp", "Aquos S3", "https://cdn-files.kimovil.com/phone_front/0002/33/thumb_132829_phone_front_big.jpeg", "Finding the best price for the Sharp Aquos S3 is no easy task. Here you will find where to buy the Sharp Aquos S3 at the best price. Prices are continuously tracked in over 140 stores so that you can find a reputable dealer with the best price", listOf(), listOf())
//        phoneDao.save(phone10)
//        val version17 = VersionPhone(null, 273F, 4, 64, phone10)
//        val version18 = VersionPhone(null, 340F, 6, 128, phone10)
//        versionPhoneDao.save(version17)
//        versionPhoneDao.save(version18)
//        val color28 = Color(null, "Black", phone10)
//        val color29 = Color(null, "Red", phone10)
//        val color30 = Color(null, "White", phone10)
//        colorPhoneDao.save(color28)
//        colorPhoneDao.save(color29)
//        colorPhoneDao.save(color30)
//
//        val phone11 = ProductStock(null, "Xiaomi", "Mi 9T", "https://cdn.phonehouse.es/res/products-image/3/7/7/5/7/377575-2221824.jpg", "Finding the best price for the Xiaomi Mi 9T is no easy task. Here you will find where to buy the Xiaomi Mi 9T at the best price. Prices are continuously tracked in over 140 stores so that you can find a reputable dealer with the best price", listOf(), listOf())
//        phoneDao.save(phone11)
//        val version19 = VersionPhone(null, 254F, 6, 64, phone11)
//        val version20 = VersionPhone(null, 280F, 6, 128, phone11)
//        versionPhoneDao.save(version19)
//        versionPhoneDao.save(version20)
//        val color31 = Color(null, "Black", phone11)
//        val color32 = Color(null, "Red", phone11)
//        val color33 = Color(null, "Blue", phone11)
//        colorPhoneDao.save(color31)
//        colorPhoneDao.save(color32)
//        colorPhoneDao.save(color33)
//
//        val phone12 = ProductStock(null, "Xiaomi", "Redmi Note 8 Pro", "https://cdn.phonehouse.es/res/products-image/4/2/3/5/5/423559-2321066.jpg", "Finding the best price for the Xiaomi Redmi Note 8 Pro is no easy task. Here you will find where to buy the Xiaomi Redmi Note 8 Pro at the best price. Prices are continuously tracked in over 140 stores so that you can find a reputable dealer with the best price", listOf(), listOf())
//        phoneDao.save(phone12)
//        val version21 = VersionPhone(null, 209F, 6, 64, phone12)
//        val version22 = VersionPhone(null, 228F, 6, 128, phone12)
//        versionPhoneDao.save(version21)
//        versionPhoneDao.save(version22)
//        val color34 = Color(null, "Black", phone12)
//        val color35 = Color(null, "Green", phone12)
//        val color36 = Color(null, "White", phone12)
//        colorPhoneDao.save(color34)
//        colorPhoneDao.save(color35)
//        colorPhoneDao.save(color36)
//
//        val phone13 = ProductStock(null, "Xiaomi", "Pocophone F1", "https://cdn.phonehouse.es/res/product450/resource_1011260.jpg", "This time Xiaomi has established a new line to follow between high-end terminals. On this occasion he has chosen to do so with a new sub-brand: POCO, and his first terminal, the PocoPhone F1. As they say they have baptized it with the name of Pocophone because they have tried to create a mobile top but removing some elements that make the product more expensive. ", listOf(), listOf())
//        phoneDao.save(phone13)
//        val version23 = VersionPhone(null, 329F, 6, 64, phone13)
//        val version24 = VersionPhone(null, 346F, 6, 128, phone13)
//        versionPhoneDao.save(version23)
//        versionPhoneDao.save(version24)
//        val color37 = Color(null, "Black", phone13)
//        val color38 = Color(null, "Blue", phone13)
//        val color39 = Color(null, "Red", phone13)
//        colorPhoneDao.save(color37)
//        colorPhoneDao.save(color38)
//        colorPhoneDao.save(color39)
//
//        val phone14 = ProductStock(null, "Asus", "ROG Phone 2 ", "https://cdn.phonehouse.es/res/products-image/3/4/6/5/3/346535-0.jpg", "Finding the best price for the Asus ROG Phone 2 is no easy task. Here you will find where to buy the Asus ROG Phone 2 at the best price. Prices are continuously tracked in over 140 stores so that you can find a reputable dealer with the best price", listOf(), listOf())
//        phoneDao.save(phone14)
//        val version25 = VersionPhone(null, 852F, 8, 128, phone14)
//        versionPhoneDao.save(version25)
//        val color40 = Color(null, "Gray", phone14)
//        colorPhoneDao.save(color40)
//
//        val phone15 = ProductStock(null, "Google", "Pixel 3", "https://cdn.phonehouse.es/res/product450/resource_1585992.jpg", "Finding the best price for the Google Pixel 3 is no easy task. Here you will find where to buy the Google Pixel 3 at the best price. Prices are continuously tracked in over 140 stores so that you can find a reputable dealer with the best price", listOf(), listOf())
//        phoneDao.save(phone15)
//        val version26 = VersionPhone(null, 504F, 4, 64, phone15)
//        val version27 = VersionPhone(null, 812F, 4, 128, phone15)
//        versionPhoneDao.save(version26)
//        versionPhoneDao.save(version27)
//        val color41 = Color(null, "Pink", phone15)
//        val color42 = Color(null, "Black", phone15)
//        colorPhoneDao.save(color41)
//        colorPhoneDao.save(color42)
//
//        val phone16 = ProductStock(null, "Google", "Pixel 3 XL", "https://cdn.phonehouse.es/res/product450/resource_1586585.jpg", "Finding the best price for the Google Pixel 3 XL is no easy task. Here you will find where to buy the Google Pixel 3 XL at the best price. Prices are continuously tracked in over 140 stores so that you can find a reputable dealer with the best price", listOf(), listOf())
//        phoneDao.save(phone16)
//        val version28 = VersionPhone(null, 528F, 4, 64, phone16)
//        val version29 = VersionPhone(null, 836F, 4, 128, phone16)
//        versionPhoneDao.save(version28)
//        versionPhoneDao.save(version29)
//        val color43 = Color(null, "White", phone16)
//        val color44 = Color(null, "Black", phone16)
//        colorPhoneDao.save(color43)
//        colorPhoneDao.save(color44)
//
//        val phone17 = ProductStock(null, "Apple", "iPhone 8", "https://cdn.phonehouse.es/res/product200/resource_347644.jpg", "Finding the best price for the Apple iPhone 8 is no easy task. Here you will find where to buy the Apple iPhone 8 at the best price. Prices are continuously tracked in over 140 stores so that you can find a reputable dealer with the best price", listOf(), listOf())
//        phoneDao.save(phone17)
//        val version30 = VersionPhone(null, 487F, 2, 64, phone17)
//        val version31 = VersionPhone(null, 594F, 2, 128, phone17)
//        val version32 = VersionPhone(null, 643F, 2, 256, phone17)
//        versionPhoneDao.save(version30)
//        versionPhoneDao.save(version31)
//        versionPhoneDao.save(version32)
//        val color45 = Color(null, "Gold", phone17)
//        val color46 = Color(null, "Gray", phone17)
//        val color47 = Color(null, "Silver", phone17)
//        colorPhoneDao.save(color45)
//        colorPhoneDao.save(color46)
//        colorPhoneDao.save(color47)
//
//        val phone18 = ProductStock(null, "Xiaomi", "Mi A3", "https://cdn.phonehouse.es/res/products-image/3/8/5/5/4/385547-2232226.jpg", "Finding the best price for the Xiaomi Mi A3 is no easy task. Here you will find where to buy the Xiaomi Mi A3 at the best price. Prices are continuously tracked in over 140 stores so that you can find a reputable dealer with the best price", listOf(), listOf())
//        phoneDao.save(phone18)
//        val version33 = VersionPhone(null, 249F, 4, 64, phone18)
//        val version34 = VersionPhone(null, 280F, 4, 128, phone18)
//        versionPhoneDao.save(version33)
//        versionPhoneDao.save(version34)
//        val color48 = Color(null, "Black", phone18)
//        val color49 = Color(null, "Blue", phone18)
//        val color50 = Color(null, "White", phone18)
//        colorPhoneDao.save(color48)
//        colorPhoneDao.save(color49)
//        colorPhoneDao.save(color50)
//    }


}