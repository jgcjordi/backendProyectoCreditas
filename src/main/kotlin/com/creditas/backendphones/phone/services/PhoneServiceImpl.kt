package com.creditas.backendphones.phone.services

import com.creditas.backendphones.phone.domain.entities.ColorPhone
import com.creditas.backendphones.phone.domain.entities.Phone
import com.creditas.backendphones.phone.domain.entities.VersionPhone
import com.creditas.backendphones.phone.domain.dao.IColorPhoneDao
import com.creditas.backendphones.phone.domain.dao.IPhoneDao
import com.creditas.backendphones.phone.domain.dao.IVersionPhoneDao
import org.apache.juli.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.*


@Service
class PhoneServiceImpl : IPhoneService {

    @Autowired
    private lateinit var phoneDao: IPhoneDao

    @Autowired
    private lateinit var colorPhoneDao: IColorPhoneDao

    @Autowired
    private lateinit var versionPhoneDao: IVersionPhoneDao
    private val LOGGER = LogFactory.getLog("PhoneServiceImpl.class")

    override fun getAllPhones(): MutableList<Phone> = phoneDao.findAll() as MutableList<Phone>

    override fun getAllPhonesPaginated(page:Int): MutableMap<String, Any> {
        val size = phoneDao.count()
        val pageSize = 6
        val pages = size/pageSize
        LOGGER.warn(pages.toString())
        val list = phoneDao.findAll(PageRequest.of(page, pageSize))
        return mutableMapOf("phoneList" to list, "totalPages" to pages, "currentPage" to page)
    }

    override fun getPhoneById(id: Int): Optional<Phone> = phoneDao.findById(id)

    override fun phonesFilteredByKeywords(stringFilter: String): MutableList<Phone> {
        val phones = phoneDao.findAll() as MutableList<Phone>
        val listKeywords: MutableList<String> = stringFilter.split(" ").toMutableList()
        for (i in listKeywords) listKeywords[listKeywords.indexOf(i)] = i.toLowerCase()

        val phonesFiltered = mutableListOf<Phone>()

        //Specific search with all keywords
        for (p in phones) {
            var add = true
            for (l in listKeywords) {
                if (!p.brand!!.toLowerCase().contains(l) &&
                        !p.model!!.toLowerCase().contains(l)) {
                    add = false
                }
            }
            if (add) phonesFiltered.add(p)
        }

        // If you don't find any results with the previous search, search with at least one word
        if (phonesFiltered.isEmpty()) {
            for (p in phones) {
                for (l in listKeywords) {
                    if (p.brand!!.toLowerCase().contains(l) ||
                            p.model!!.toLowerCase().contains(l)) {
                        phonesFiltered.add(p)
                        break
                    }
                }
            }
        }
        return phonesFiltered
    }




    override fun setPhonesExample() {
        val phone1 = Phone(null, "Apple", "Iphone X", "https://cdn.phonehouse.es/res/product450/resource_350051.jpg", "Finding the best price for the Apple iPhone X is no easy task. Here you will find where to buy the Apple iPhone X at the best price. Prices are continuously tracked in over 140 stores so that you can find a reputable dealer with the best price", listOf(), listOf())
        phoneDao.save(phone1)
        val version1 = VersionPhone(null, 939F, 3, 64, phone1)
        val version2 = VersionPhone(null, 1129F, 3, 265, phone1)
        versionPhoneDao.save(version1)
        versionPhoneDao.save(version2)
        val color1 = ColorPhone(null, "Space Gray", phone1)
        val color2 = ColorPhone(null, "Silver", phone1)
        colorPhoneDao.save(color1)
        colorPhoneDao.save(color2)


        val phone2 = Phone(null, "Apple", "IPhone 11", "https://cdn-files.kimovil.com/phone_front/0003/47/thumb_246000_phone_front_big.jpeg", "Finding the best price for the Apple iPhone 11 is no easy task. Here you will find where to buy the Apple iPhone 11 at the best price. Prices are continuously tracked in over 140 stores so that you can find a reputable dealer with the best price", listOf(), listOf())
        phoneDao.save(phone2)
        val version3 = VersionPhone(null, 797F, 4, 64, phone2)
        val version4 = VersionPhone(null, 826F, 4, 128, phone2)
        val version5 = VersionPhone(null, 979F, 4, 256, phone2)
        versionPhoneDao.save(version3)
        versionPhoneDao.save(version4)
        versionPhoneDao.save(version5)
        val color3 = ColorPhone(null, "Black", phone2)
        val color4 = ColorPhone(null, "Green", phone2)
        val color5 = ColorPhone(null, "Purple", phone2)
        val color6 = ColorPhone(null, "Red", phone2)
        val color7 = ColorPhone(null, "White", phone2)
        val color8 = ColorPhone(null, "Yellow", phone2)
        colorPhoneDao.save(color3)
        colorPhoneDao.save(color4)
        colorPhoneDao.save(color5)
        colorPhoneDao.save(color6)
        colorPhoneDao.save(color7)
        colorPhoneDao.save(color8)

        val phone3 = Phone(null, "Samsung", "Galaxy S10", "https://cdn.phonehouse.es/res/product450/resource_1477411.jpg", "Finding the best price for the Samsung Galaxy S10 is no easy task. Here you will find where to buy the Samsung Galaxy S10 at the best price. Prices are continuously tracked in over 140 stores so that you can find a reputable dealer with the best price", listOf(), listOf())
        phoneDao.save(phone3)
        val version6 = VersionPhone(null, 850F, 8, 128, phone3)
        val version7 = VersionPhone(null, 1260F, 8, 512, phone3)
        versionPhoneDao.save(version6)
        versionPhoneDao.save(version7)
        val color9 = ColorPhone(null, "Prism black", phone3)
        val color10 = ColorPhone(null, "Prism green", phone3)
        val color11 = ColorPhone(null, "Prism white", phone3)
        val color12 = ColorPhone(null, "Prism blue", phone3)
        val color13 = ColorPhone(null, "Prism silver", phone3)
        val color14 = ColorPhone(null, "Cardinal Red", phone3)
        colorPhoneDao.save(color9)
        colorPhoneDao.save(color10)
        colorPhoneDao.save(color11)
        colorPhoneDao.save(color12)
        colorPhoneDao.save(color13)
        colorPhoneDao.save(color14)

        val phone4 = Phone(null, "Samsung", "Galaxy A70", "https://cdn.phonehouse.es/res/product450/resource_1891171.jpg", "Finding the best price for the Samsung Galaxy A70 is no easy task. Here you will find where to buy the Samsung Galaxy A70 at the best price. Prices are continuously tracked in over 140 stores so that you can find a reputable dealer with the best price", listOf(), listOf())
        phoneDao.save(phone4)
        val version8 = VersionPhone(null, 320F, 6, 128, phone4)
        val version9 = VersionPhone(null, 360F, 8, 128, phone4)
        versionPhoneDao.save(version8)
        versionPhoneDao.save(version9)
        val color15 = ColorPhone(null, "Black", phone4)
        val color16 = ColorPhone(null, "Blue", phone4)
        val color17 = ColorPhone(null, "White", phone4)
        colorPhoneDao.save(color15)
        colorPhoneDao.save(color16)
        colorPhoneDao.save(color17)

        val phone5 = Phone(null, "Alcatel", "1S", "https://cdn.phonehouse.es/res/product450/resource_1764921.jpg", "Finding the best price for the Alcatel 1S is no easy task. Here you will find where to buy the Alcatel 1S at the best price. Prices are continuously tracked in over 140 stores so that you can find a reputable dealer with the best price", listOf(), listOf())
        phoneDao.save(phone5)
        val version10 = VersionPhone(null, 86F, 3, 32, phone5)
        val version11 = VersionPhone(null, 110F, 4, 64, phone5)
        versionPhoneDao.save(version10)
        versionPhoneDao.save(version11)
        val color18 = ColorPhone(null, "Black", phone5)
        val color19 = ColorPhone(null, "Blue", phone5)
        colorPhoneDao.save(color18)
        colorPhoneDao.save(color19)

        val phone6 = Phone(null, "Alcatel", "3", "https://cdn.phonehouse.es/res/product450/resource_364555.jpg", "Finding the best price for the Alcatel 3 (2019) is no easy task. Here you will find where to buy the Alcatel 3 (2019) at the best price. Prices are continuously tracked in over 140 stores so that you can find a reputable dealer with the best price", listOf(), listOf())
        phoneDao.save(phone6)
        val version12 = VersionPhone(null, 138F, 3, 32, phone6)
        val version13 = VersionPhone(null, 161F, 4, 64, phone6)
        versionPhoneDao.save(version12)
        versionPhoneDao.save(version13)
        val color20 = ColorPhone(null, "Black", phone6)
        val color21 = ColorPhone(null, "Blue", phone6)
        colorPhoneDao.save(color20)
        colorPhoneDao.save(color21)

        val phone7 = Phone(null, "Motorola", "Moto G7 Plus", "https://cdn.phonehouse.es/res/viewtwo450/resource_1964334.jpg", "Finding the best price for the Motorola Moto G7 Plus is no easy task. Here you will find where to buy the Motorola Moto G7 Plus at the best price. Prices are continuously tracked in over 140 stores so that you can find a reputable dealer with the best price", listOf(), listOf())
        phoneDao.save(phone7)
        val version14 = VersionPhone(null, 216F, 4, 64, phone7)
        versionPhoneDao.save(version14)
        val color22 = ColorPhone(null, "Blue", phone7)
        val color23 = ColorPhone(null, "Red", phone7)
        colorPhoneDao.save(color22)
        colorPhoneDao.save(color23)

        val phone8 = Phone(null, "Motorola", "One Vision", "https://cdn.phonehouse.es/res/products-image/3/8/3/0/5/383058-2228084.jpg", "Finding the best price for the Motorola One Vision is no easy task. Here you will find where to buy the Motorola One Vision at the best price. Prices are continuously tracked in over 140 stores so that you can find a reputable dealer with the best price", listOf(), listOf())
        phoneDao.save(phone8)
        val version15 = VersionPhone(null, 199F, 4, 64, phone8)
        versionPhoneDao.save(version15)
        val color24 = ColorPhone(null, "Blue", phone8)
        val color25 = ColorPhone(null, "Brown", phone8)
        colorPhoneDao.save(color24)
        colorPhoneDao.save(color25)

        val phone9 = Phone(null, "Sharp", "Aquos C10", "https://cdn-files.kimovil.com/phone_front/0002/56/thumb_155997_phone_front_big.jpeg", "Finding the best price for the Sharp Aquos C10 is no easy task. Here you will find where to buy the Sharp Aquos C10 at the best price. Prices are continuously tracked in over 140 stores so that you can find a reputable dealer with the best price", listOf(), listOf())
        phoneDao.save(phone9)
        val version16 = VersionPhone(null, 100F, 4, 64, phone9)
        versionPhoneDao.save(version16)
        val color26 = ColorPhone(null, "Black", phone9)
        val color27 = ColorPhone(null, "White", phone9)
        colorPhoneDao.save(color26)
        colorPhoneDao.save(color27)

        val phone10 = Phone(null, "Sharp", "Aquos S3", "https://cdn-files.kimovil.com/phone_front/0002/33/thumb_132829_phone_front_big.jpeg", "Finding the best price for the Sharp Aquos S3 is no easy task. Here you will find where to buy the Sharp Aquos S3 at the best price. Prices are continuously tracked in over 140 stores so that you can find a reputable dealer with the best price", listOf(), listOf())
        phoneDao.save(phone10)
        val version17 = VersionPhone(null, 273F, 4, 64, phone10)
        val version18 = VersionPhone(null, 340F, 6, 128, phone10)
        versionPhoneDao.save(version17)
        versionPhoneDao.save(version18)
        val color28 = ColorPhone(null, "Black", phone10)
        val color29 = ColorPhone(null, "Red", phone10)
        val color30 = ColorPhone(null, "White", phone10)
        colorPhoneDao.save(color28)
        colorPhoneDao.save(color29)
        colorPhoneDao.save(color30)

        val phone11 = Phone(null, "Xiaomi", "Mi 9T", "https://cdn.phonehouse.es/res/products-image/3/7/7/5/7/377575-2221824.jpg", "Finding the best price for the Xiaomi Mi 9T is no easy task. Here you will find where to buy the Xiaomi Mi 9T at the best price. Prices are continuously tracked in over 140 stores so that you can find a reputable dealer with the best price", listOf(), listOf())
        phoneDao.save(phone11)
        val version19 = VersionPhone(null, 254F, 6, 64, phone11)
        val version20 = VersionPhone(null, 280F, 6, 128, phone11)
        versionPhoneDao.save(version19)
        versionPhoneDao.save(version20)
        val color31 = ColorPhone(null, "Black", phone11)
        val color32 = ColorPhone(null, "Red", phone11)
        val color33 = ColorPhone(null, "Blue", phone11)
        colorPhoneDao.save(color31)
        colorPhoneDao.save(color32)
        colorPhoneDao.save(color33)

        val phone12 = Phone(null, "Xiaomi", "Redmi Note 8 Pro", "https://cdn.phonehouse.es/res/products-image/4/2/3/5/5/423559-2321066.jpg", "Finding the best price for the Xiaomi Redmi Note 8 Pro is no easy task. Here you will find where to buy the Xiaomi Redmi Note 8 Pro at the best price. Prices are continuously tracked in over 140 stores so that you can find a reputable dealer with the best price", listOf(), listOf())
        phoneDao.save(phone12)
        val version21 = VersionPhone(null, 209F, 6, 64, phone12)
        val version22 = VersionPhone(null, 228F, 6, 128, phone12)
        versionPhoneDao.save(version21)
        versionPhoneDao.save(version22)
        val color34 = ColorPhone(null, "Black", phone12)
        val color35 = ColorPhone(null, "Green", phone12)
        val color36 = ColorPhone(null, "White", phone12)
        colorPhoneDao.save(color34)
        colorPhoneDao.save(color35)
        colorPhoneDao.save(color36)


    }


}