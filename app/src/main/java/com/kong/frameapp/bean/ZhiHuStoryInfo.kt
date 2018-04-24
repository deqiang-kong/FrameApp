package com.kong.frameapp.bean

import java.io.Serializable

/**
 * zhihu
 * Created by kongdq on 17/9/14.
 */
data class ZhiHuStoryInfo(var id: Int) : Serializable {


    //时间
    var date: String? = null
    var type: Int = 0
    var ga_prefix: String? = null
    var title: String? = null
    var images: List<String>? = null

}