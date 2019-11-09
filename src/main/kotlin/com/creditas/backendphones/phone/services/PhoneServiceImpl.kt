package com.creditas.backendphones.phone.services

import com.creditas.backendphones.phone.domain.entities.ColorPhone
import com.creditas.backendphones.phone.domain.entities.Phone
import com.creditas.backendphones.phone.domain.entities.VersionPhone
import com.creditas.backendphones.phone.domain.entities.dao.IColorPhoneDao
import com.creditas.backendphones.phone.domain.entities.dao.IPhoneDao
import com.creditas.backendphones.phone.domain.entities.dao.IVersionPhoneDao
import org.apache.juli.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class PhoneServiceImpl : IPhoneService {

    @Autowired
    private lateinit var phoneDao: IPhoneDao

    @Autowired
    private lateinit var colorPhoneDao: IColorPhoneDao

    @Autowired
    private lateinit var versionPhoneDao: IVersionPhoneDao
    private val LOGGER = LogFactory.getLog("PhoneServiceImpl.class")

    //@Transactional(readOnly = true)//Se le puede indicar que solo es de lectura
    override fun getPhones(): MutableList<Phone>{
        val aux = phoneDao.findAll() as MutableList<Phone>
        return aux
    }

    override fun setPhonesExample() {
        val phone1 = Phone(null, "Apple", "Iphone X", "Datos sobre IPhone", "https://cdn.phonehouse.es/res/product450/resource_350051.jpg", listOf(), listOf())
        phoneDao.save(phone1)
        val version1 = VersionPhone(null, 939F, 3, 64, phone1)
        val version2 = VersionPhone(null, 1129F, 3, 265, phone1)
        versionPhoneDao.save(version1)
        versionPhoneDao.save(version2)
        val color1 = ColorPhone(null, "Gris Espacial", phone1)
        val color2 = ColorPhone(null, "Plata", phone1)
        colorPhoneDao.save(color1)
        colorPhoneDao.save(color2)


        val phone2 = Phone(null, "Apple", "IPhone 11", "Datos sobre IPhone 11", "https://cdn.phonehouse.es/res/products-image/4/2/0/3/2/420324-2308064.jpg", listOf(), listOf())
        phoneDao.save(phone2)
        val version3 = VersionPhone(null, 797F, 4, 64, phone2)
        val version4 = VersionPhone(null, 826F, 4, 128, phone2)
        val version5 = VersionPhone(null, 979F, 5, 256, phone2)
        versionPhoneDao.save(version3)
        versionPhoneDao.save(version4)
        versionPhoneDao.save(version5)
        val color3 = ColorPhone(null, "Negro", phone2)
        val color4 = ColorPhone(null, "Verde", phone2)
        val color5 = ColorPhone(null, "Morado", phone2)
        val color6 = ColorPhone(null, "Rojo", phone2)
        val color7 = ColorPhone(null, "Blanco", phone2)
        val color8 = ColorPhone(null, "Amarillo", phone2)
        colorPhoneDao.save(color3)
        colorPhoneDao.save(color4)
        colorPhoneDao.save(color5)
        colorPhoneDao.save(color6)
        colorPhoneDao.save(color7)
        colorPhoneDao.save(color8)
    }


}