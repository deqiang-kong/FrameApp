package com.kong.frameapp.bean

import java.io.Serializable



data class MovieInfoBean(var id: String) : Serializable {

    var showStyle: String? = null
    var loadType: String? = null
    var changeOpenFlag: String? = null
    var line: String? = null

    var showType: String? = null
    var moreURL: String? = null
    var title: String? = null
    var bigPicShowFlag: String? = null

    var childList: List<MovieDescription>? = null


    inner class MovieDescription : Serializable{

        var airTime: String? = null
        var duration: String? = null
        var loadType: String? = null
        var score: String? = null

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