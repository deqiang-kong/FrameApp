package com.kong.frameapp.bean

import java.io.Serializable

/**
 * Created by kaipai on 17/9/13.
 */
data class VideoMainBean(var id: String) : Serializable {


    var dataType: Int = 0
    var hotSearchList: List<HotSearchList>? = null
    var list: List<MovieInfoBean>? = null


    inner class HotSearchList : Serializable {

        var refCounter: String? = null
        var cnname: String? = null
        var siteId: String? = null
        var simplename: String? = null

        var id: String? = null
        var tagName: String? = null
        var createdtime: String? = null
        var enname: String? = null

    }
}