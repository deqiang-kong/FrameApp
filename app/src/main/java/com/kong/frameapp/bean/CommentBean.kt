package com.kong.frameapp.bean

import java.io.Serializable


data class CommentBean(var pnum: Int) : Serializable {

    var totalRecords: Int = 0
    var records: Int = 0
    var totalPnum: Int = 0
    var list: List<ListBean>? = null


    inner class ListBean : Serializable {

        var msg: String? = null
        var phoneNumber: String? = null
        var dataId: String? = null
        var userPic: String? = null
        var time: String? = null
        var likeNum: Int = 0
    }
}