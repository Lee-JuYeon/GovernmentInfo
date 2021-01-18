package com.universeindustry.governmentinfo.online.retrofit.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Path
import org.simpleframework.xml.Root

@Root(name = "items", strict = false)
data class Items @JvmOverloads constructor (
        /*
        inline
        =  you're specifying that the elements you want parsed are in the same parent tree of a target element
        =  구문 분석할 요소가 대상 요소의 동일한 상위 트리에 있는지 지정.
         */
        @ElementList(inline = true, name = "item", required = false)
//        @param:ElementList(inline = true, name = "item")
        val itemList: MutableList<Item>? = null

)

@Root(name = "item", strict = false)
data class Item @JvmOverloads constructor  (
        @Element(name = "jmcd", required = false)
//        @param:Element(name = "jmcd", required = false)
        val jmcd: Int = 0,

        @Element(name = "jmfldnm", required = false)
//        @param:Element(name = "jmfldnm", required = false)
        val jmfldnm: String? = null,

        @Element(name = "mdobligfldcd", required = false)
//        @param:Element(name = "mdobligfldcd", required = false)
        val mdobligfldcd: Int = 0,

        @Element(name = "mdobligfldnm", required = false)
//        @param:Element(name = "mdobligfldnm", required = false)
        val mdobligfldnm: String? = null,

        @Element(name = "obligfldcd", required = false)
//        @param:Element(name = "obligfldcd", required = false)
        val obligfldcd: Int = 0,

        @Element(name = "obligfldnm", required = false)
//        @param:Element(name = "obligfldnm", required = false)
        val obligfldnm: String? = null,

        @Element(name = "qualgbcd", required = false)
//        @param:Element(name = "qualgbcd", required = false)
        val qualgbcd: Int = 0,

        @Element(name = "qualgbnm", required = false)
//        @param:Element(name = "qualgbnm", required = false)
        val qualgbnm: String? = null,

        @Element(name = "seriescd", required = false)
//        @param:Element(name = "seriescd", required = false)
        val seriescd: String? = null,

        @Element(name = "seriesnm", required = false)
//        @param:Element(name = "seriesnm", required = false)
        val seriesnm: String? = null
)

/*
@PropertyElement is bind with an XML tag
@Element for binding another child of the tag
@Attribute for binding the attribute of the XML tag
@PropertyElement(name = “artist”, converter = HtmlEscapeStringConverter::class ) for HTML content

@Root(name = "items", strict = false)
data class Items (
        /*
        inline
        =  you're specifying that the elements you want parsed are in the same parent tree of a target element
        =  구문 분석할 요소가 대상 요소의 동일한 상위 트리에 있는지 지정.
         */
        @field:ElementList(inline = true, required = false)
        @param:ElementList(inline = true, name = "item")
        val itemList: MutableList<Item>? = null

)

@Root(name = "item", strict = false)
data class Item  (
        @field:Element(name = "jmcd", required = false)
        @param:Element(name = "jmcd", required = false)
        val jmcd: Int = 0,

        @field:Element(name = "jmfldnm", required = false)
        @param:Element(name = "jmfldnm", required = false)
        val jmfldnm: String? = null,

        @field:Element(name = "mdobligfldcd", required = false)
        @param:Element(name = "mdobligfldcd", required = false)
        val mdobligfldcd: Int = 0,

        @field:Element(name = "mdobligfldnm", required = false)
        @param:Element(name = "mdobligfldnm", required = false)
        val mdobligfldnm: String? = null,

        @field:Element(name = "obligfldcd", required = false)
        @param:Element(name = "obligfldcd", required = false)
        val obligfldcd: Int = 0,

        @field:Element(name = "obligfldnm", required = false)
        @param:Element(name = "obligfldnm", required = false)
        val obligfldnm: String? = null,

        @field:Element(name = "qualgbcd", required = false)
        @param:Element(name = "qualgbcd", required = false)
        val qualgbcd: Int = 0,

        @field:Element(name = "qualgbnm", required = false)
        @param:Element(name = "qualgbnm", required = false)
        val qualgbnm: String? = null,

        @field:Element(name = "seriescd", required = false)
        @param:Element(name = "seriescd", required = false)
        val seriescd: String? = null,

        @field:Element(name = "seriesnm", required = false)
        @param:Element(name = "seriesnm", required = false)
        val seriesnm: String? = null
)

 */