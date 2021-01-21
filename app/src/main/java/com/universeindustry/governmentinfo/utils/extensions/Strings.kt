package com.universeindustry.governmentinfo.utils.extensions

object Strings {
    val menu : String = "메인뷰"
    val funding : String = "지원금"
    val license : String = "국가 자격증"
    val vaccine : String = "예방 접종"
    val house : String = "주거"
    val bank : String = "은행"


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