package com.kong.frameapp.bean

import java.io.Serializable

/**
 * 基础响应信息
 * @param <T>
 */
data class CustomNewsResponse<T>(var showapi_res_code : Int? = null) : Serializable {


    var showapi_res_error: String? = null
    var showapi_res_body: T? = null


}