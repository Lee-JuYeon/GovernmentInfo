package com.universeindustry.governmentinfo.utils.datatype

import java.net.URL
import java.net.URLDecoder
import java.net.URLEncoder

object TypeConverting {
    fun String.toURL() : String {
        return URLEncoder.encode(this, "UTF-8")
    }
    fun String.toDecodedURL() : String {
        return URLDecoder.decode(this,"UTF-8")
    }
}