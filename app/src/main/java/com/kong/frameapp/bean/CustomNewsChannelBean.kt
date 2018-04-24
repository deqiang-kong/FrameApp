package com.kong.frameapp.bean

import java.io.Serializable


data class CustomNewsChannelBean(var totalNum: Int) : Serializable {

    //var totalNum: Int = 0
    var ret_code:  Int = 0
    var channelList: List<ChannelInfo>? = null

}