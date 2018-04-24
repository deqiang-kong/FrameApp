package com.kong.frameapp.bean

import java.io.Serializable


data class MyNewsChannelBean(var myChannelList: List<ChannelInfo>? = null) : Serializable {


   // var myChannelList: List<ChannelInfo>? = null
    var otherChannelList: List<ChannelInfo>? = null


}