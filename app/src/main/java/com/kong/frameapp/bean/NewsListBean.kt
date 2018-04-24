package com.kong.frameapp.bean

import com.google.gson.annotations.SerializedName
import com.kong.framesamples.modules.wynews.NewsConstants
import java.io.Serializable
import java.util.*

/**
 * 新闻列表，根据类型
 * Created by kaipai on 17/9/13.
 */
data class NewsListBean(var channelId: String?=null) : Serializable {


    @SerializedName(NewsConstants.NEWS_TYPE_CODE_HEADLINE)
    val headlineList: ArrayList<NewsBean>? = null

    @SerializedName(NewsConstants.NEWS_TYPE_CODE_TEC) val tecList: ArrayList<NewsBean>? = null

    @SerializedName(NewsConstants.NEWS_TYPE_CODE_SPORT) val sportList: ArrayList<NewsBean>? = null

    @SerializedName(NewsConstants.NEWS_TYPE_CODE_HEALTH) val healthList: ArrayList<NewsBean>? = null

    @SerializedName(NewsConstants.NEWS_TYPE_CODE_DADA) val dadaList: ArrayList<NewsBean>? = null

    @SerializedName(NewsConstants.NEWS_TYPE_CODE_MILITARY) val militaryList: ArrayList<NewsBean>? = null

    @SerializedName(NewsConstants.NEWS_TYPE_CODE_TRAVEL) val travelList: ArrayList<NewsBean>? = null


    fun getNewsList(channelId: String): ArrayList<NewsBean>? {
        var newsList: ArrayList<NewsBean>? = null
        when (channelId) {
            NewsConstants.NEWS_TYPE_CODE_HEADLINE -> newsList = headlineList
            NewsConstants.NEWS_TYPE_CODE_TEC -> newsList = tecList
            NewsConstants.NEWS_TYPE_CODE_SPORT -> newsList = sportList
            NewsConstants.NEWS_TYPE_CODE_HEALTH -> newsList = healthList
            NewsConstants.NEWS_TYPE_CODE_DADA -> newsList = dadaList
            NewsConstants.NEWS_TYPE_CODE_MILITARY -> newsList = militaryList
            NewsConstants.NEWS_TYPE_CODE_TRAVEL -> newsList = travelList
            else -> newsList = headlineList
        }

        return newsList
    }

}