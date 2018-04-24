package com.kong.frameapp.bean

import java.io.Serializable
import java.util.*


data class VideoTypeListBean(var pnum: Int) : Serializable {

    var totalRecords: Int = 0
    var records: Int = 0
    var totalPnum: Int = 0
    var list: ArrayList<ListBean>? = null


    inner class ListBean : Serializable {

        var airTime: Int = 0
        var duration: String? = null
        var loadtype: String? = null
        var score: Float = 0.toFloat()
        var angleIcon: String? = null
        var dataId: String? = null
        var description: String? = null
        var loadURL: String? = null
        var shareURL: String? = null
        var pic: String? = null
        var title: String? = null
        var roomId: String? = null
    }


}