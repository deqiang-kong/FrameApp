package com.kong.frameapp.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

/**
 * Created by kaipai on 17/9/14.
 */

data class ZhiHuDailyBean(var id: String)    : Serializable {
    private val serialVersionUID = -828322761336296999L


    @SerializedName("date")
    var date: String? = null

    @SerializedName("stories")
    lateinit var stories: ArrayList<ZhiHuStoryInfo>

    @SerializedName("top_stories")
    lateinit var top_stories: ArrayList<ZhiHuTopStory>
}