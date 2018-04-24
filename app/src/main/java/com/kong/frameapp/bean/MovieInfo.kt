package com.kong.frameapp.bean

import android.text.TextUtils
import java.io.Serializable


data class MovieInfo(var couponNum: Int) : Serializable {

    var airTime: Int = 0
    var ultraClearURL: String? = null
    var HDURL: String? = null
    var SDURL: String? = null
    var smoothURL: String? = null

    var director: String? = null
    var videoType: String? = null
    var downloadURL: String? = null
    var htmlURL: String? = null
    var description: String? = null
    var title: String? = null

    var duration: String? = null
    var actors: String? = null
    var list: List<ListBean>? = null

    fun getVideoUrl(): String? {
        var videoUrl: String? = null
        if (!TextUtils.isEmpty(HDURL)) {
            videoUrl = HDURL
        } else if (!TextUtils.isEmpty(SDURL)) {
            videoUrl = SDURL
        } else if (!TextUtils.isEmpty(smoothURL)) {
            videoUrl = smoothURL
        }else
            videoUrl=""

        return videoUrl
    }

    data class ListBean( var showType: String?) : Serializable {

        //var showType: String? = null
        var title: String? = null
        var childList: List<ChildListBean>? = null
    }


    inner class ChildListBean : Serializable {

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
    }
}