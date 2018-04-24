package com.kong.frameapp.bean

import java.io.Serializable


/**
 * 新闻频道信息
 */
data class ChannelInfo(var name: String? = null) : Serializable {


    var channelId: String? = null
    //var name: String? = null
    var type: Int = 0

}