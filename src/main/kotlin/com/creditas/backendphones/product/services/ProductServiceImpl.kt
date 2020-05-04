package com.creditas.backendphones.product.services

import com.creditas.backendphones.product.domain.dao.*
import com.creditas.backendphones.product.domain.entities.*
import org.apache.juli.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service


@Service
class ProductServiceImpl : IProductService {

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

    override fun getAllCheapestModelsWithStock():  MutableList<ProductStock> {
        return productStockDao.findAllCheapestModelsWithStock()
    }

    override fun getAllCheapestModelsWithStockPaged(page: Int): Page<ProductStock> {
        return productStockDao.findAllCheapestModelsWithStockPaged(PageRequest.of(page, 6))
    }

    override fun getAllProductsOfThisModelWithStockOrderedByPrice(model: Int): MutableList<ProductStock> {
        return productStockDao.findAllProductsOfThisModelWithStockOrderedByPrice(model)
    }

    override fun getStockProductById(id: Int): ProductStock {
        return productStockDao.findById(id).get()
    }

    override fun saveStockProduct(productStock: ProductStock) {
        productStockDao.save(productStock)
    }

    override fun productsFilteredByKeywords(stringFilter: String): MutableList<ProductStock> {
        val products = getAllCheapestModelsWithStock()
        val listKeywords: MutableList<String> = stringFilter.split(" ").toMutableList()
        for (i in listKeywords) listKeywords[listKeywords.indexOf(i)] = i.toLowerCase()

        val phonesFiltered = mutableListOf<ProductStock>()

        //Specific search with all keywords
        for (p in products) {
            var add = true
            for (l in listKeywords) {
                if (!p.brand!!.name.toLowerCase().contains(l) &&
                        !p.model!!.name.toLowerCase().contains(l)) {
                    add = false
                }
            }
            if (add) phonesFiltered.add(p)
        }

        // If you don't find any results with the previous search, search with at least one word
        if (phonesFiltered.isEmpty()) {
            for (p in products) {
                for (l in listKeywords) {
                    if (p.brand!!.name.toLowerCase().contains(l) ||
                            p.model!!.name.toLowerCase().contains(l)) {
                        phonesFiltered.add(p)
                        break
                    }
                }
            }
        }
        return phonesFiltered
    }

    override fun setBdPhonesExample() {
        val brandGoogle = Brand(null, "Google")
        brandDao.save(brandGoogle)
        val brandXiaomi = Brand(null, "Xiaomi")
        brandDao.save(brandXiaomi)
        val brandAsus = Brand(null, "Asus")
        brandDao.save(brandAsus)
        val brandApple = Brand(null, "Apple")
        brandDao.save(brandApple)
        val brandSamsung = Brand(null, "Samsung")
        brandDao.save(brandSamsung)
        val brandAlcatel = Brand(null, "Alcatel")
        brandDao.save(brandAlcatel)
        val brandMotorola = Brand(null, "Motorola")
        brandDao.save(brandMotorola)
        val brandSharp = Brand(null, "Sharp")
        brandDao.save(brandSharp)

        val ram2 = Ram(null, 2)
        ramDao.save(ram2)
        val ram3 = Ram(null, 3)
        ramDao.save(ram3)
        val ram4 = Ram(null, 4)
        ramDao.save(ram4)
        val ram6 = Ram(null, 6)
        ramDao.save(ram6)
        val ram8 = Ram(null, 8)
        ramDao.save(ram8)


        val storage32 = Storage(null, 32)
        storageDao.save(storage32)
        val storage64 = Storage(null, 64)
        storageDao.save(storage64)
        val storage128 = Storage(null, 128)
        storageDao.save(storage128)
        val storage256 = Storage(null, 256)
        storageDao.save(storage256)
        val storage512 = Storage(null, 512)
        storageDao.save(storage512)

        val colorBlack = Color(null, "Black")
        colorDao.save(colorBlack)
        val colorWhite = Color(null, "White")
        colorDao.save(colorWhite)
        val colorPink = Color(null, "Pink")
        colorDao.save(colorPink)
        val colorBlue = Color(null, "Blue")
        colorDao.save(colorBlue)
        val colorRed = Color(null, "Red")
        colorDao.save(colorRed)
        val colorGreen = Color(null, "Green")
        colorDao.save(colorGreen)
        val colorSilver = Color(null, "Silver")
        colorDao.save(colorSilver)
        val colorGray = Color(null, "Gray")
        colorDao.save(colorGray)
        val colorYellow = Color(null, "Yellow")
        colorDao.save(colorYellow)


        val mPixel3 = Model(null, "Pixel 3",
                "Finding the best price for the Google Pixel 3 is no easy task. Here you will find where to buy the Google Pixel 3 at the best price. Prices are continuously tracked in over 140 stores so that you can find a reputable dealer with the best price",
                "https://raw.githubusercontent.com/jgcjordi/backendProyectoCreditas/master/img-phones/pixel-3.jpg")
        modelDao.save(mPixel3)
        productStockDao.save(ProductStock(null, 5, 0, 504F, mPixel3, brandGoogle, colorBlack, ram4, storage64))
        productStockDao.save(ProductStock(null, 5, 0, 514F, mPixel3, brandGoogle, colorPink, ram4, storage64))
        productStockDao.save(ProductStock(null, 5, 0, 812F, mPixel3, brandGoogle, colorBlack, ram4, storage128))
        productStockDao.save(ProductStock(null, 5, 0, 822F, mPixel3, brandGoogle, colorPink, ram4, storage128))

        val mPixel3Xl = Model(null, "Pixel 3 XL",
                "Finding the best price for the Google Pixel 3 XL is no easy task. Here you will find where to buy the Google Pixel 3 XL at the best price. Prices are continuously tracked in over 140 stores so that you can find a reputable dealer with the best price",
                "https://raw.githubusercontent.com/jgcjordi/backendProyectoCreditas/master/img-phones/pixel-3-xl.jpg")
        modelDao.save(mPixel3Xl)
        productStockDao.save(ProductStock(null, 5, 0, 528F, mPixel3Xl, brandGoogle, colorBlack, ram4, storage64))
        productStockDao.save(ProductStock(null, 5, 0, 538F, mPixel3Xl, brandGoogle, colorWhite, ram4, storage64))
        productStockDao.save(ProductStock(null, 5, 0, 832F, mPixel3Xl, brandGoogle, colorBlack, ram4, storage128))
        productStockDao.save(ProductStock(null, 5, 0, 838F, mPixel3Xl, brandGoogle, colorWhite, ram4, storage128))

        val mMiA3 = Model(null, "Mi A3",
                "Finding the best price for the Xiaomi Mi A3 is no easy task. Here you will find where to buy the Xiaomi Mi A3 at the best price. Prices are continuously tracked in over 140 stores so that you can find a reputable dealer with the best price",
                "https://raw.githubusercontent.com/jgcjordi/backendProyectoCreditas/master/img-phones/mi-3a.jpg")
        modelDao.save(mMiA3)
        productStockDao.save(ProductStock(null, 5, 0, 249F, mMiA3, brandXiaomi, colorBlack, ram4, storage64))
        productStockDao.save(ProductStock(null, 5, 0, 252F, mMiA3, brandXiaomi, colorWhite, ram4, storage64))
        productStockDao.save(ProductStock(null, 5, 0, 240F, mMiA3, brandXiaomi, colorBlue, ram4, storage64))
        productStockDao.save(ProductStock(null, 5, 0, 280F, mMiA3, brandXiaomi, colorBlack, ram4, storage128))
        productStockDao.save(ProductStock(null, 5, 0, 288F, mMiA3, brandXiaomi, colorWhite, ram4, storage128))
        productStockDao.save(ProductStock(null, 5, 0, 281F, mMiA3, brandXiaomi, colorBlue, ram4, storage128))


        val mMi9T = Model(null, "Mi 9T",
                "Finding the best price for the Xiaomi Mi 9T is no easy task. Here you will find where to buy the Xiaomi Mi 9T at the best price. Prices are continuously tracked in over 140 stores so that you can find a reputable dealer with the best price",
                "https://raw.githubusercontent.com/jgcjordi/backendProyectoCreditas/master/img-phones/mi-9t.jpg")
        modelDao.save(mMi9T)
        productStockDao.save(ProductStock(null, 5, 0, 254F, mMi9T, brandXiaomi, colorBlack, ram6, storage64))
        productStockDao.save(ProductStock(null, 5, 0, 260F, mMi9T, brandXiaomi, colorRed, ram6, storage64))
        productStockDao.save(ProductStock(null, 5, 0, 255F, mMi9T, brandXiaomi, colorBlue, ram6, storage64))
        productStockDao.save(ProductStock(null, 5, 0, 282F, mMi9T, brandXiaomi, colorBlack, ram6, storage128))
        productStockDao.save(ProductStock(null, 5, 0, 288F, mMi9T, brandXiaomi, colorRed, ram6, storage128))
        productStockDao.save(ProductStock(null, 5, 0, 281F, mMi9T, brandXiaomi, colorBlue, ram6, storage128))


        val mPocophoneF1 = Model(null, "Pocophone F1",
                "This time Xiaomi has established a new line to follow between high-end terminals. On this occasion he has chosen to do so with a new sub-brand: POCO, and his first terminal, the PocoPhone F1. As they say they have baptized it with the name of Pocophone because they have tried to create a mobile top but removing some elements that make the product more expensive.",
                "https://raw.githubusercontent.com/jgcjordi/backendProyectoCreditas/master/img-phones/pocofone-f1.jpg")
        modelDao.save(mPocophoneF1)
        productStockDao.save(ProductStock(null, 5, 0, 329F, mPocophoneF1, brandXiaomi, colorBlack, ram6, storage64))
        productStockDao.save(ProductStock(null, 5, 0, 330F, mPocophoneF1, brandXiaomi, colorRed, ram6, storage64))
        productStockDao.save(ProductStock(null, 5, 0, 335F, mPocophoneF1, brandXiaomi, colorBlue, ram6, storage64))
        productStockDao.save(ProductStock(null, 5, 0, 346F, mPocophoneF1, brandXiaomi, colorBlack, ram6, storage128))
        productStockDao.save(ProductStock(null, 5, 0, 350F, mPocophoneF1, brandXiaomi, colorRed, ram6, storage128))
        productStockDao.save(ProductStock(null, 5, 0, 368F, mPocophoneF1, brandXiaomi, colorBlue, ram6, storage128))

        val mRedmiNote8Pro = Model(null, "Redmi Note 8 Pro",
                "Finding the best price for the Xiaomi Redmi Note 8 Pro is no easy task. Here you will find where to buy the Xiaomi Redmi Note 8 Pro at the best price. Prices are continuously tracked in over 140 stores so that you can find a reputable dealer with the best price",
                "https://raw.githubusercontent.com/jgcjordi/backendProyectoCreditas/master/img-phones/redmi-note-8-pro.jpg")
        modelDao.save(mRedmiNote8Pro)
        productStockDao.save(ProductStock(null, 5, 0, 215F, mRedmiNote8Pro, brandXiaomi, colorBlack, ram6, storage64))
        productStockDao.save(ProductStock(null, 5, 0, 209F, mRedmiNote8Pro, brandXiaomi, colorGreen, ram6, storage64))
        productStockDao.save(ProductStock(null, 5, 0, 235F, mRedmiNote8Pro, brandXiaomi, colorWhite, ram6, storage64))
        productStockDao.save(ProductStock(null, 5, 0, 220F, mRedmiNote8Pro, brandXiaomi, colorBlack, ram6, storage128))
        productStockDao.save(ProductStock(null, 5, 0, 228F, mRedmiNote8Pro, brandXiaomi, colorGreen, ram6, storage128))
        productStockDao.save(ProductStock(null, 5, 0, 248F, mRedmiNote8Pro, brandXiaomi, colorWhite, ram6, storage128))

        val mRogPhone2 = Model(null, "ROG Phone 2",
                "Finding the best price for the Asus ROG Phone 2 is no easy task. Here you will find where to buy the Asus ROG Phone 2 at the best price. Prices are continuously tracked in over 140 stores so that you can find a reputable dealer with the best price",
                "https://raw.githubusercontent.com/jgcjordi/backendProyectoCreditas/master/img-phones/rog-phone-2.jpg")
        modelDao.save(mRogPhone2)
        productStockDao.save(ProductStock(null, 5, 0, 852F, mRogPhone2, brandAsus, colorGray, ram8, storage128))


        val mIphoneX = Model(null, "Iphone X",
                "Finding the best price for the Apple iPhone X is no easy task. Here you will find where to buy the Apple iPhone X at the best price. Prices are continuously tracked in over 140 stores so that you can find a reputable dealer with the best price",
                "https://raw.githubusercontent.com/jgcjordi/backendProyectoCreditas/master/img-phones/iphone-x.jpg")
        modelDao.save(mIphoneX)
        productStockDao.save(ProductStock(null, 5, 0, 930F, mIphoneX, brandApple, colorSilver, ram3, storage64))
        productStockDao.save(ProductStock(null, 5, 0, 939F, mIphoneX, brandApple, colorGray, ram3, storage64))
        productStockDao.save(ProductStock(null, 5, 0, 1129F, mIphoneX, brandApple, colorSilver, ram3, storage256))
        productStockDao.save(ProductStock(null, 5, 0, 1130F, mIphoneX, brandApple, colorGray, ram3, storage256))

        val mIphone11 = Model(null, "IPhone 11",
                "Finding the best price for the Apple iPhone 11 is no easy task. Here you will find where to buy the Apple iPhone 11 at the best price. Prices are continuously tracked in over 140 stores so that you can find a reputable dealer with the best price",
                "https://raw.githubusercontent.com/jgcjordi/backendProyectoCreditas/master/img-phones/iphone-11.jpeg")
        modelDao.save(mIphone11)
        productStockDao.save(ProductStock(null, 5, 0, 797F, mIphone11, brandApple, colorRed, ram4, storage64))
        productStockDao.save(ProductStock(null, 5, 0, 796F, mIphone11, brandApple, colorGreen, ram4, storage64))
        productStockDao.save(ProductStock(null, 5, 0, 799F, mIphone11, brandApple, colorPink, ram4, storage64))
        productStockDao.save(ProductStock(null, 5, 0, 798F, mIphone11, brandApple, colorYellow, ram4, storage64))
        productStockDao.save(ProductStock(null, 5, 0, 826F, mIphone11, brandApple, colorYellow, ram4, storage128))
        productStockDao.save(ProductStock(null, 5, 0, 979F, mIphone11, brandApple, colorYellow, ram4, storage256))

        val mIphone8 = Model(null, "Iphone 8",
                "Finding the best price for the Apple iPhone 8 is no easy task. Here you will find where to buy the Apple iPhone 8 at the best price. Prices are continuously tracked in over 140 stores so that you can find a reputable dealer with the best price",
                "https://cdn.phonehouse.es/res/product200/resource_347644.jpg")
        modelDao.save(mIphone8)
        productStockDao.save(ProductStock(null, 5, 0, 486F, mIphone8, brandApple, colorSilver, ram2, storage64))
        productStockDao.save(ProductStock(null, 5, 0, 487F, mIphone8, brandApple, colorGray, ram2, storage64))
        productStockDao.save(ProductStock(null, 5, 0, 590F, mIphone8, brandApple, colorSilver, ram2, storage128))
        productStockDao.save(ProductStock(null, 5, 0, 594F, mIphone8, brandApple, colorGray, ram2, storage128))
        productStockDao.save(ProductStock(null, 5, 0, 640F, mIphone8, brandApple, colorSilver, ram2, storage256))
        productStockDao.save(ProductStock(null, 5, 0, 643F, mIphone8, brandApple, colorGray, ram2, storage256))

        val mGalaxyS10 = Model(null, "Galaxy S10",
                "Finding the best price for the Samsung Galaxy S10 is no easy task. Here you will find where to buy the Samsung Galaxy S10 at the best price. Prices are continuously tracked in over 140 stores so that you can find a reputable dealer with the best price",
                "https://raw.githubusercontent.com/jgcjordi/backendProyectoCreditas/master/img-phones/galaxy-s10.jpg")
        modelDao.save(mGalaxyS10)
        productStockDao.save(ProductStock(null, 5, 0, 1260F, mGalaxyS10, brandSamsung, colorSilver, ram8, storage512))

        val mGalaxyA70 = Model(null, "Galaxy A70",
                "Finding the best price for the Samsung Galaxy A70 is no easy task. Here you will find where to buy the Samsung Galaxy A70 at the best price. Prices are continuously tracked in over 140 stores so that you can find a reputable dealer with the best price",
                "https://raw.githubusercontent.com/jgcjordi/backendProyectoCreditas/master/img-phones/galaxy-a70.jpg")
        modelDao.save(mGalaxyA70)
        productStockDao.save(ProductStock(null, 5, 0, 382F, mGalaxyA70, brandSamsung, colorBlue, ram8, storage128))

        val m1S = Model(null, "1S",
                "Finding the best price for the Alcatel 1S is no easy task. Here you will find where to buy the Alcatel 1S at the best price. Prices are continuously tracked in over 140 stores so that you can find a reputable dealer with the best price",
                "https://raw.githubusercontent.com/jgcjordi/backendProyectoCreditas/master/img-phones/1s.jpg")
        modelDao.save(m1S)
        productStockDao.save(ProductStock(null, 5, 0, 360F, m1S, brandAlcatel, colorBlue, ram3, storage32))
    }
//
//        val phone6 = ProductStock(null, "Alcatel", "3", "https://raw.githubusercontent.com/jgcjordi/backendProyectoCreditas/master/img-phones/alcatel-3.jpg", "Finding the best price for the Alcatel 3 (2019) is no easy task. Here you will find where to buy the Alcatel 3 (2019) at the best price. Prices are continuously tracked in over 140 stores so that you can find a reputable dealer with the best price", listOf(), listOf())
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
//        val phone7 = ProductStock(null, "Motorola", "Moto G7 Plus", "https://raw.githubusercontent.com/jgcjordi/backendProyectoCreditas/master/img-phones/moto-g7-plus.jpg", "Finding the best price for the Motorola Moto G7 Plus is no easy task. Here you will find where to buy the Motorola Moto G7 Plus at the best price. Prices are continuously tracked in over 140 stores so that you can find a reputable dealer with the best price", listOf(), listOf())
//        phoneDao.save(phone7)
//        val version14 = VersionPhone(null, 216F, 4, 64, phone7)
//        versionPhoneDao.save(version14)
//        val color22 = Color(null, "Blue", phone7)
//        val color23 = Color(null, "Red", phone7)
//        colorPhoneDao.save(color22)
//        colorPhoneDao.save(color23)
//
//        val phone8 = ProductStock(null, "Motorola", "One Vision", "https://raw.githubusercontent.com/jgcjordi/backendProyectoCreditas/master/img-phones/one-vision.jpg", "Finding the best price for the Motorola One Vision is no easy task. Here you will find where to buy the Motorola One Vision at the best price. Prices are continuously tracked in over 140 stores so that you can find a reputable dealer with the best price", listOf(), listOf())
//        phoneDao.save(phone8)
//        val version15 = VersionPhone(null, 199F, 4, 64, phone8)
//        versionPhoneDao.save(version15)
//        val color24 = Color(null, "Blue", phone8)
//        val color25 = Color(null, "Brown", phone8)
//        colorPhoneDao.save(color24)
//        colorPhoneDao.save(color25)
//
//        val phone9 = ProductStock(null, "Sharp", "Aquos C10", "https://raw.githubusercontent.com/jgcjordi/backendProyectoCreditas/master/img-phones/aquos-c10.jpeg", "Finding the best price for the Sharp Aquos C10 is no easy task. Here you will find where to buy the Sharp Aquos C10 at the best price. Prices are continuously tracked in over 140 stores so that you can find a reputable dealer with the best price", listOf(), listOf())
//        phoneDao.save(phone9)
//        val version16 = VersionPhone(null, 100F, 4, 64, phone9)
//        versionPhoneDao.save(version16)
//        val color26 = Color(null, "Black", phone9)
//        val color27 = Color(null, "White", phone9)
//        colorPhoneDao.save(color26)
//        colorPhoneDao.save(color27)
//
//        val phone10 = ProductStock(null, "Sharp", "Aquos S3", "https://raw.githubusercontent.com/jgcjordi/backendProyectoCreditas/master/img-phones/aquos-s3.jpeg", "Finding the best price for the Sharp Aquos S3 is no easy task. Here you will find where to buy the Sharp Aquos S3 at the best price. Prices are continuously tracked in over 140 stores so that you can find a reputable dealer with the best price", listOf(), listOf())
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


//    }


}