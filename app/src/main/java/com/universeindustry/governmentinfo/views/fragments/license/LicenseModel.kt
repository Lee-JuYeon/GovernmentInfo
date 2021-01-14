package com.universeindustry.governmentinfo.views.fragments.license

data class LicenseModel(
    val title : String?,
    val examFee : String?,
    val writeenTestDate : HashMap<String, String>?,
    val practicalTestDate : HashMap<String, String>?
)
//{
//    override fun toString(): String {
//        return super.toString()
//    }
//}