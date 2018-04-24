package com.kong.frameapp.modules.zhihu.adapter;

import android.view.View;

import com.kong.fmklibrary.base.BaseHolder;
import com.kong.fmklibrary.base.DefaultAdapter;
import com.kong.fmklibrary.controls.magicrecyclerView.BaseItem;
import com.kong.frameapp.R;
import com.kong.frameapp.bean.ZhiHuDailyBean;
import com.kong.frameapp.bean.ZhiHuStoryInfo;
import com.kong.frameapp.modules.zhihu.holder.TestItemHolder;

import java.util.List;

import javax.inject.Inject;


public class TestAdapter  extends DefaultAdapter<ZhiHuStoryInfo> {


    public TestAdapter(List<ZhiHuStoryInfo> infos) {
        super(infos);
    }

    @Override
    public BaseHolder<ZhiHuStoryInfo> getHolder(View view, int viewType) {
        return new TestItemHolder(view);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.zhihu_story_item;
    }


//    private Context mContext;
//    private int image_width;
//    private int image_height;
//
//
//    public TestAdapter(Fragment fragment) {
//        mContext = fragment.getContext();
//        float width = fragment.getResources().getDimension(R.dimen.news_image_width);
//        //float width = 100;
//        image_width = (int) width;
//        image_height = image_width * 3 / 4;
//    }
//
//    @Override
//    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
//        View view;
//        if (viewType == TYPE_TAGS) {
//            view = LayoutInflater.from(mContext).inflate(R.layout.zhihu_story_date_tag, parent, false);
//            return new TitleHolder(view);
//        } else {
//            view = LayoutInflater.from(mContext).inflate(R.layout.zhihu_story_item, parent, false);
//            return new ViewHolder(view);
//        }
//    }
//
//
//    @Override
//    public void onBind(RecyclerView.ViewHolder viewHolder, int RealPosition, BaseItem data) {
//        //普通内容
//        if (data.getItemType() == TYPE_NORMAL) {
//            ZhiHuStoryInfo story = (ZhiHuStoryInfo) data.getData();
//            ViewHolder holder = (ViewHolder) viewHolder;
//            holder.mNewsTitle.setText(story.getTitle());
//            PictureLoadUtil.getInstance().displayImageView(mContext, story.getImages().get(0), holder.mNewsImage);
//        }
//        //日期标签
//        else if (data.getItemType() == TYPE_TAGS) {
//            String title = (String) data.getData();
//            int year = Integer.parseInt(title.substring(0, 4));
//            int mon = Integer.parseInt(title.substring(4, 6));
//            int day = Integer.parseInt(title.substring(6, 8));
//            Calendar calendar = Calendar.getInstance();
//            calendar.set(year, mon - 1, day);
//            TitleHolder holder = (TitleHolder) viewHolder;
//            DateFormat df = SimpleDateFormat.getDateInstance();
//            holder.mItemTitle.setText(df.format(calendar.getTime()) );
//        }
//    }
//
//
//    class TitleHolder extends Holder {
//        TextView mItemTitle;
//
//        TitleHolder(View view) {
//            super(view);
//            //
//            mItemTitle=view.findViewById(R.id.item_title);
//        }
//    }
//
//    class ViewHolder extends Holder {
//
//        ImageView mNewsImage;
//        TextView mNewsTitle;
//
//        ViewHolder(View view) {
//            super(view);
//
//            mNewsImage=view.findViewById(R.id.news_image);
//            mNewsTitle=view.findViewById(R.id.news_title);
//        }
//    }
}