package com.kong.frameapp.bean

import java.io.Serializable


/**
 * 电影实体包装类
 */
data class MovieResponse<T>(var code: String) : Serializable {

    var msg: String? = null
    var ret: T? = null

}