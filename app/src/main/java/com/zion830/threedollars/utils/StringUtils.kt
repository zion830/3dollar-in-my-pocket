package com.zion830.threedollars.utils

import androidx.annotation.StringRes
import com.zion830.threedollars.GlobalApplication
import com.zion830.threedollars.R
import com.zion830.threedollars.repository.model.MenuType
import java.util.*

object StringUtils {

    @JvmStatic
    fun getString(@StringRes resId: Int) = GlobalApplication.getContext().getString(resId)

    @JvmStatic
    fun getStringArray(resId: Int) = GlobalApplication.getContext().resources.getStringArray(resId)

    @JvmStatic
    fun toReadableString(value: Float) = if (value.toInt().toFloat() == value) {
        value.toInt().toString()
    } else {
        value.toString()
    }

    @JvmStatic
    fun getBearerTokenString(accessToken: String) = "Bearer $accessToken"

    @JvmStatic
    fun getReadableCategory(category: String?): String {
        if (category == null) {
            return getString(R.string.none)
        }

        return getString(
            when (category.toUpperCase(Locale.ROOT)) {
                "BUNGEOPPANG" -> R.string.bung
                "TAKOYAKI" -> R.string.tako
                "HOTTEOK" -> R.string.hodduck
                else -> R.string.gye
            }
        )
    }

    @JvmStatic
    fun getMenuTitle(menuType: MenuType): String {
        val stringNameArray = getStringArray(R.array.menu_name)
        val index = when (menuType) {
            MenuType.BUNGEOPPANG -> 0
            MenuType.TAKOYAKI -> 1
            MenuType.GYERANPPANG -> 2
            else -> 3
        }

        return stringNameArray[index]
    }

    @JvmStatic
    fun getMenuDescription(menuType: MenuType): String {
        val stringDescArray = getStringArray(R.array.menu_desc)
        val index = when (menuType) {
            MenuType.BUNGEOPPANG -> 0
            MenuType.TAKOYAKI -> 1
            MenuType.GYERANPPANG -> 2
            else -> 3
        }

        return stringDescArray[index]
    }
}
