package com.kong.frameapp.bean

import java.io.Serializable

/**
 * zhihu
 * Created by kongdq on 17/9/14.
 */
data class ZhiHuTopStory(var id: Int) : Serializable {


    var image: String? = null
    var type: Int = 0
    var ga_prefix: String? = null
    var title: String? = null
}