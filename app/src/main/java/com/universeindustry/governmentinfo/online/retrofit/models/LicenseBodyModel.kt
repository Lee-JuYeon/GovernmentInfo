package com.universeindustry.governmentinfo.online.retrofit.models

import com.universeindustry.governmentinfo.views.fragments.license.LicenseModel
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "items", strict = false)
data class LicenseBodyModel @JvmOverloads constructor (
        @field:Element(name = "item")
        @param:Element(name = "item")
        val bodyElement: LicenseModel? = null
)