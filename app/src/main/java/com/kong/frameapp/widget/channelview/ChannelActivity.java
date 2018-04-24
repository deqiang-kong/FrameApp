package com.kong.frameapp.widget.channelview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import com.kong.frameapp.R;
import com.kong.frameapp.bean.ChannelInfo;
import com.kong.frameapp.widget.channelview.helper.ItemDragHelperCallback;

import java.util.ArrayList;
import java.util.List;



/**
 * 频道 增删改查 排序
 */
public class ChannelActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_channel_list);

        mRecyclerView =  findViewById(R.id.recy);
        initData();
    }

    private void initData() {
        final List<ChannelInfo> myItems = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            ChannelInfo entity = new ChannelInfo();
            entity.setName("频道");

            if(i<3)
                entity.setType(1);
            myItems.add(entity);
        }
        final List<ChannelInfo> otherItems = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            ChannelInfo entity = new ChannelInfo();
            entity.setName("其他新闻" + i);
            otherItems.add(entity);
        }



        GridLayoutManager manager = new GridLayoutManager(this, 4);
        mRecyclerView.setLayoutManager(manager);

        ItemDragHelperCallback callback = new ItemDragHelperCallback();
        final ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(mRecyclerView);

        final ChannelAdapter adapter = new ChannelAdapter(this, helper, myItems, otherItems);
        mRecyclerView.setAdapter(adapter);


        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int viewType = adapter.getItemViewType(position);
                return viewType == ChannelAdapter.TYPE_MY || viewType == ChannelAdapter.TYPE_OTHER ? 1 : 4;
            }
        });


        adapter.setOnMyChannelItemClickListener(new ChannelAdapter.OnMyChannelItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(ChannelActivity.this, myItems.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });

        List d=adapter.getMyChannelItems();
    }
}
