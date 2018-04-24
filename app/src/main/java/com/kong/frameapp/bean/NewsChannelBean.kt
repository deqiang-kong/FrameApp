package com.kong.frameapp.bean

import java.io.Serializable


/**
 * 新闻频道信息
 */
data class NewsChannelBean(var couponNum: Int) : Serializable {

    var airTime: Boolean = true
    var list: List<ChannelInfo>? = null


//    data class ListBean( var showType: String?) : Serializable {
//
//        //var showType: String? = null
//        var title: String? = null
//        var childList: List<ChildListBean>? = null
//    }


    data  class ChannelInfo(var channelName: String) : Serializable {

        //var channelName: String? = null
        var channelId: String? = null
        var channelType: String? = null
        var channelSelect: Boolean = false
        var channelIndex: Int = 0
        var channelFixed: Boolean? = null
    }
}