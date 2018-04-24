package com.kong.frameapp.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * zhihu
 * Created by kongdq on 17/9/14.
 */
data class ZhiHuStoryDetailBean(var id: Int) : Serializable {


    @SerializedName("body")
    var body: String? = null
    @SerializedName("image_source")
    var image_source: String? = null
    @SerializedName("title")
    var title: String? = null
    @SerializedName("image")
    var image: String? = null
    @SerializedName("share_url")
    var share_url: String? = null
    @SerializedName("ga_prefix")
    var ga_prefix: String? = null
    @SerializedName("type")
    var type: String? = null
    @SerializedName("js")
    var js: Array<String>? = null
    @SerializedName("images")
    var images: Array<String>? = null
    @SerializedName("css")
    var css: Array<String>? = null

}