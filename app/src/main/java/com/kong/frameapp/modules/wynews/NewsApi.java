package com.kong.frameapp.modules.wynews;


import java.util.List;
import java.util.Map;

public interface NewsApi {



    /**
     * 请求新闻列表 例子：http://c.m.163.com/nc/article/headline/T1348647909107/0-20.html
     *
     * @param type      新闻类别：headline为头条,local为北京本地,fangchan为房产,list为其他
     * @param id        新闻类别id
     * @param startPage 开始的页码
     * @return 被观察对象
     */
//    @GET("nc/article/{type}/{id}/{startPage}-20.html")
//    Observable<Map<String, List<NeteastNewsSummary>>> getNewsList(
//            @Path("type") String type,
//            @Path("id") String id,
//            @Path("startPage") int startPage);

    /**
     * 知乎首页接口
     */
    public final static String HOME_PAGE = "http://news-at.zhihu.com/api/4/news/latest";

    /**
     * 知乎首页加载更多接口
     */
    public final static String HOME_PAGE_MORE = "http://news-at.zhihu.com/api/4/news/before/";

    /**
     * 知乎首页加载更多接口
     */
    public final static String ZHIHU_STORY_DETAIL = "http://news-at.zhihu.com/api/4/news/";


    /**
     * 新闻列表接口
     */
    public final static String NEWS_TYPE_LIST = "http://c.m.163.com/nc/article/list/";


}
