package com.kong.frameapp.bean

import java.io.Serializable

/**
 * 基础响应信息
 * @param <T>
 */
data class BaseResponse<T>(var flg : Int) : Serializable {
    private val serialVersionUID = -828322761336296999L


    //var flg: Int = 0
    var msg: String? = null
    var result: T? = null


    override fun toString(): String {
        return "LzyResponse{\n" + //

                "\tcode=" + flg + "\n" + //

                "\tmsg='" + msg + "\'\n" + //

                "\tdata=" + result + "\n" + //

                '}'
    }
}