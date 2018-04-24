package com.kong.frameapp.modules.video;



public interface MovieApi {


    /**
     * 电影首页接口
     */
    public final static String HOME_PAGE = "http://api.svipmovie.com/front/homePageApi/homePage.do";


    /**
     * 电影分类列表接口
     */
    public final static String VIDEO_TYPE_LIST = "http://api.svipmovie.com/front/columns/getVideoList.do";

    /**
     * 电影信息接口
     */
    public final static String VIDEO_DETAIL =  "http://api.svipmovie.com/front/videoDetailApi/videoDetail.do";


    /**
     * 电影评论接口
     */
    public final static String VIDEO_COMMENT =  "http://api.svipmovie.com/front/Commentary/getCommentList.do";



}
