package com.kong.framesamples.modules.customnnews

/**
 * 新闻相关常量
 * Created by kongdq on 17/10/19.
 */
object NewsConstants {


    /**
     * 网易新闻类型
     */
    const val NEWS_TYPE_HEADLINE = "头条"
    val NEWS_TYPE_TEC = "科技"
    val NEWS_TYPE_SPORT = "体育"
    val NEWS_TYPE_HEALTH = "健康"
    val NEWS_TYPE_DADA = "轻松一刻"
    val NEWS_TYPE_MILITARY = "军事"
    val NEWS_TYPE_TRAVEL = "旅游"


    /**
     * 网易新闻类型码
     */
    const val NEWS_TYPE_CODE_HEADLINE = "T1348647909107"//头条
    const val NEWS_TYPE_CODE_TEC = "T1348649580692"//科技
    const val NEWS_TYPE_CODE_SPORT = "T1348649079062"//体育
    const val NEWS_TYPE_CODE_HEALTH = "T1414389941036"//健康
    const val NEWS_TYPE_CODE_DADA = "T1350383429665"//轻松一刻
    const val NEWS_TYPE_CODE_MILITARY = "T1348648141035"//军事
    const val NEWS_TYPE_CODE_TRAVEL = "T1348654204705"//旅游


    fun getNewsTypeCode(title: String): String =
            when (title) {
                NewsConstants.NEWS_TYPE_HEADLINE -> NewsConstants.NEWS_TYPE_CODE_HEADLINE
                NewsConstants.NEWS_TYPE_TEC -> NewsConstants.NEWS_TYPE_CODE_TEC
                NewsConstants.NEWS_TYPE_SPORT -> NewsConstants.NEWS_TYPE_CODE_SPORT
                NewsConstants.NEWS_TYPE_HEALTH -> NewsConstants.NEWS_TYPE_CODE_HEALTH
                NewsConstants.NEWS_TYPE_DADA -> NewsConstants.NEWS_TYPE_CODE_DADA
                NewsConstants.NEWS_TYPE_MILITARY -> NewsConstants.NEWS_TYPE_CODE_MILITARY
                NewsConstants.NEWS_TYPE_TRAVEL -> NewsConstants.NEWS_TYPE_CODE_TRAVEL
                else -> NewsConstants.NEWS_TYPE_CODE_HEADLINE
            }

    val CACHE_NEWS_FILE = "top_news_cache_file"
    val CACHE_HEADLINE_NEWS = "headline_news_cache"
    val CACHE_TEC_NEWS = "tec_news_cache"
    val CACHE_SPORT_NEWS = "sport_news_cache"
    val CACHE_RECOMMEND_NEWS = "recommend_news_cache"
    val CACHE_CARTOON_NEWS = "cartoon_news_cache"
    val CACHE_MILITARY_NEWS = "military_news_cache"
    val CACHE_TRAVEL_NEWS = "travel_news_cache"




}