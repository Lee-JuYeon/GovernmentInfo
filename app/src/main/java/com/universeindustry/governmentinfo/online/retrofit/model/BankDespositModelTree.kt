package com.universeindustry.governmentinfo.online.retrofit.model

import java.io.Serializable

data class BankDespositModelTree(
        val itemTItle : String?,
        val bankTitle : String,
        val type : String?,
        val saving : String?,
        val vip : String?,
        val term : String?,
        val code : String?
) : Serializable

//class BankDespositModelTree {
//    var name: String? = null
//    var messages: Array<String>
//    var blog: String? = null
//    var age: String? = null
//
//    override fun toString(): String {
//        return "ClassPojo [name = $name, messages = $messages, blog = $blog, age = $age]"
//    }
//}