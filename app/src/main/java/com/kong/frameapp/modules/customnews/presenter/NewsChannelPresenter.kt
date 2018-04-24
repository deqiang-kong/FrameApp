package com.kong.frameapp.modules.customnews.presenter

import android.app.Application
import com.kong.fmklibrary.integration.AppManager
import com.kong.fmklibrary.mvp.BasePresenter
import com.kong.frameapp.bean.ChannelInfo
import com.kong.frameapp.bean.CustomNewsChannelBean
import com.kong.frameapp.bean.MyNewsChannelBean
import com.kong.frameapp.modules.customnews.contract.NewsChannelContract
import com.kong.frameapp.net.ResultCallBack
import javax.inject.Inject


/**
 * 新闻频道管理Presenter
 * Created by kaipai on 17/10/12.
 */
class NewsChannelPresenter @Inject constructor(model: NewsChannelContract.Model, rootView: NewsChannelContract.View, appManager: AppManager, application: Application) : BasePresenter<NewsChannelContract.Model, NewsChannelContract.View>(model, rootView), NewsChannelContract.Presenter {


    //    private var mAppManager: AppManager? = null
    //    private var mApplication: Application? = null
    //var date: String? = null


    override fun getData(isRefresh: Boolean) {

        mModel.requestData(isRefresh, null, object : ResultCallBack<CustomNewsChannelBean> {
            override fun onSuccess(dataObj: CustomNewsChannelBean) {

                mRootView.refreshSuc(getMyChannelList(dataObj))
            }

            override fun onFail(errorCode: Int, errorMsg: String) {
                //mRootView.refreshFail(errorMsg);
            }
        })
    }


    fun getMyChannelList(newsChannels: CustomNewsChannelBean) : MyNewsChannelBean {
        var totalNum = newsChannels.totalNum
        var myNewsChannelBean= MyNewsChannelBean()

        if (totalNum > 0) {

            val myChannelList = ArrayList<ChannelInfo>()
            val otherChannelList = ArrayList<ChannelInfo>()

            val myList = getMyList()

            for (bean in newsChannels.channelList!!) {

                var status = false
                for (mChannel in myList!!) {
                    if (bean.name.equals(mChannel.name)) {
                        bean.type = mChannel.type
                        myChannelList.add(bean)
                        myList.remove(mChannel)
                        status = true
                        break
                    }
                }

                if (!status) {
                    otherChannelList.add(bean)
                }
            }

            myNewsChannelBean!!.myChannelList=myChannelList
            myNewsChannelBean!!.otherChannelList=otherChannelList
        }

        return myNewsChannelBean!!
    }

    fun getMyList(): ArrayList<ChannelInfo> {

        var myChannelList= ArrayList<ChannelInfo>()

        val entity = ChannelInfo()
        entity.name = "频道"

        var bean1= ChannelInfo()
        bean1.name = "国内焦点"
        bean1.type = 1;
        myChannelList!!.add(bean1)

        var bean2= ChannelInfo()
        bean2!!.name = "国际焦点"
        bean2.type = 1;
        myChannelList!!.add(bean2)

        var bean3= ChannelInfo()
        bean3!!.name = "互联网焦点"
        bean3.type = 0;
        myChannelList!!.add(bean3)

        myChannelList!!.add(entity)

        return myChannelList
    }


}