package com.universeindustry.governmentinfo.views.fragments.license

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root
import java.io.Serializable

@Root(name = "items", strict = false)
data class LicenseModel(
        @field:Element(name = "jmfldnm")
        @param:Element(name = "jmfldnm")
        val title : String?,
        val examFee : String?,
        val writeenTestDate : HashMap<String, String>?,
        val practicalTestDate : HashMap<String, String>?
) : Serializable
//{
//    override fun toString(): String {
//        return super.toString()
//    }
//}