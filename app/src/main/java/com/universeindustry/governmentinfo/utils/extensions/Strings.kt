package com.universeindustry.governmentinfo.utils.extensions

object Strings {
    val funding : String = "지원금"
    val career : String = "스펙, 자격증"
    val medical : String = "의료보험"
    val jobSearching : String = "구인구직"
    val realEstate : String = "주거"
    val banking : String = "적금추천"


    // 문자열이 제이슨 형태인지
    fun String?.isJsonObject():Boolean {
        return this?.startsWith("{") == true && this.endsWith("}")
//    return this?.startsWith("{") == true && this.endsWith("}")
    }
//fun String?.isJsonObject():Boolean = this?.startsWith("{") == true && this.endsWith("}")

    // 문자열이 제이슨 배열인지
    fun String?.isJsonArray() : Boolean {
        return this?.startsWith("[") == true && this.endsWith("]")
    }

}