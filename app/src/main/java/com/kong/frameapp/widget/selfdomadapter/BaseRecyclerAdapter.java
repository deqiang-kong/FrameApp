package com.kong.frameapp.widget.selfdomadapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kong.frameapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: BaseRecyclerAdapter
 * Fuction: RecyclerView通用适配器<p>
 * CreateDate:2016/2/16 22:47<p>
 * UpdateUser:<p>
 * UpdateDate:<p>
 */
public abstract class BaseRecyclerAdapter extends RecyclerView.Adapter<BaseRecyclerViewHolder> {


    public static final int TYPE_HEADER = 1;
    public static final int TYPE_MORE = 3;
    public static final int TYPE_EMPTY = 4;
    private static final int TYPE_MORE_FAIL = 5;

    public static final int TYPE_ITEM = 2;

    protected List<BaseAdapterItem> mData;
    protected Context mContext;
    protected LayoutInflater mInflater;

    private RecyclerView.LayoutManager mLayoutManager;
    private OnRecyclerActionListener mRecyclerAdapterListener;

    protected boolean mShowLoadMoreView;
    protected boolean mShowEmptyView;


    private int mLastPosition = -1;
    private String mExtraMsg;
    public int mMoreItemCount;
    private Boolean mEnableLoadMore;


    public BaseRecyclerAdapter(Context context, List<BaseAdapterItem> data) {
        this(context, data, null);
    }

    public BaseRecyclerAdapter(Context context, List<BaseAdapterItem> data, RecyclerView.LayoutManager layoutManager) {
        mContext = context;
        mLayoutManager = layoutManager;
        mData = data == null ? new ArrayList<BaseAdapterItem>() : data;
        mInflater = LayoutInflater.from(context);
    }


    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //
        if (viewType == TYPE_MORE) {
            return new BaseRecyclerViewHolder(mContext, mInflater.inflate(R.layout.item_load_more, parent, false));
        } else if (viewType == TYPE_MORE_FAIL) {
            final BaseRecyclerViewHolder holder = new BaseRecyclerViewHolder(mContext, mInflater.inflate(R.layout.item_load_more_failed, parent, false));
            if (mRecyclerAdapterListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mEnableLoadMore = true;
                        mShowLoadMoreView = true;
                        notifyItemChanged(getItemCount() - 1);
                        holder.itemView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mRecyclerAdapterListener.onLoadMore();
                            }
                        }, 400);
                    }
                });
            }
            return holder;
        } else if (viewType == TYPE_EMPTY) {
            final BaseRecyclerViewHolder holder = new BaseRecyclerViewHolder(mContext, mInflater.inflate(R.layout.item_empty_view, parent, false));
            if (mRecyclerAdapterListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mRecyclerAdapterListener.onEmptyClick();
                    }
                });
            }
            return holder;
        } else {

            final BaseRecyclerViewHolder holder = new BaseRecyclerViewHolder(mContext, mInflater.inflate(getItemLayoutId(viewType), parent, false));
            if (mRecyclerAdapterListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (holder.getLayoutPosition() != RecyclerView.NO_POSITION) {
                            try {
                                mRecyclerAdapterListener.onItemClick(holder.getLayoutPosition(),mData.get(holder.getLayoutPosition()));
                            } catch (IndexOutOfBoundsException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
            return holder;
        }
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {

        if (getItemViewType(position) == TYPE_HEADER) {
            fullSpan(holder, TYPE_HEADER);
        }
        else if (getItemViewType(position) == TYPE_MORE) {
            fullSpan(holder, TYPE_MORE);
        } else if (getItemViewType(position) == TYPE_MORE_FAIL) {
            fullSpan(holder, TYPE_MORE_FAIL);
            holder.setText(R.id.tv_failed, mExtraMsg + "请点击重试！");
        } else if (getItemViewType(position) == TYPE_EMPTY) {
            fullSpan(holder, TYPE_EMPTY);
            holder.setText(R.id.tv_error, mExtraMsg);
        } else {
            bindData(holder, position, mData.get(position));
        }

        if (!mShowEmptyView && mRecyclerAdapterListener != null && (mEnableLoadMore != null && mEnableLoadMore) && !mShowLoadMoreView && position == getItemCount() - 1 && getItemCount() >= mMoreItemCount) {
            mShowLoadMoreView = true;
            holder.itemView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mRecyclerAdapterListener.onLoadMore();
                    notifyItemInserted(getItemCount());
                }
            }, 400);
        }

    }

    private void fullSpan(BaseRecyclerViewHolder holder, final int type) {
        if (mLayoutManager != null) {
            if (mLayoutManager instanceof StaggeredGridLayoutManager) {
                if (((StaggeredGridLayoutManager) mLayoutManager).getSpanCount() != 1) {
                    StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
                    params.setFullSpan(true);
                }
            } else if (mLayoutManager instanceof GridLayoutManager) {
                final GridLayoutManager gridLayoutManager = (GridLayoutManager) mLayoutManager;
                final GridLayoutManager.SpanSizeLookup oldSizeLookup = gridLayoutManager.getSpanSizeLookup();
                gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        if (getItemViewType(position) == type) {
                            return gridLayoutManager.getSpanCount();
                        }
                        if (oldSizeLookup != null) {
                            return oldSizeLookup.getSpanSize(position);
                        }
                        return 1;
                    }
                });
            }
        }
    }

    @Override
    public void onViewDetachedFromWindow(BaseRecyclerViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
    }

    public void add(int pos, BaseAdapterItem item) {
        mData.add(pos, item);
        notifyItemInserted(pos);
    }

    public void delete(int pos) {
        mData.remove(pos);
        notifyItemRemoved(pos);
    }

    public void addMoreData(List<BaseAdapterItem> data) {
        int startPos = mData.size();
        mData.addAll(data);
        notifyItemRangeInserted(startPos, data.size());
    }

    public List<BaseAdapterItem> getData() {
        return mData;
    }

    public void setData(List<BaseAdapterItem> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {

        if (mShowEmptyView) {
            return TYPE_EMPTY;
        }

        if (mRecyclerAdapterListener != null && (mEnableLoadMore != null && mEnableLoadMore) && mShowLoadMoreView && getItemCount() - 1 == position) {
            return TYPE_MORE;
        }

        if (mRecyclerAdapterListener != null && !mShowLoadMoreView && (mEnableLoadMore != null && !mEnableLoadMore) && getItemCount() - 1 == position) {
            return TYPE_MORE_FAIL;
        }

        return bindViewType(position);
    }

    @Override
    public int getItemCount() {
        int i = mRecyclerAdapterListener == null || mEnableLoadMore == null ? 0 : (mEnableLoadMore && mShowLoadMoreView) || (!mShowLoadMoreView && !mEnableLoadMore) ? 1 : 0;
        return mShowEmptyView ? 1 : mData != null ? mData.size() + i : 0;
    }

    public abstract int getItemLayoutId(int viewType);

    public abstract void bindData(BaseRecyclerViewHolder holder, int position, BaseAdapterItem item);

    protected int bindViewType(int position) {
        return 0;
    }

    public void showEmptyView(boolean showEmptyView, String msg) {
        mShowEmptyView = showEmptyView;
        mExtraMsg = msg;
    }


    public void setRecyclerActionListener(OnRecyclerActionListener listener) {
        mRecyclerAdapterListener = listener;
        mMoreItemCount = 20;
        mEnableLoadMore = true;
    }


    public void loadMoreSuccess() {
        mEnableLoadMore = true;
        mShowLoadMoreView = false;
        notifyItemRemoved(getItemCount());
    }

    public void loadMoreFailed(String errorMsg) {
        mEnableLoadMore = false;
        mShowLoadMoreView = false;
        mExtraMsg = errorMsg;
        notifyItemChanged(getItemCount() - 1);
    }

    /**
     * 设置是否开启底部加载
     *
     * @param enableLoadMore true为开启；false为关闭；null为已经全部加载完毕的关闭
     */
    public void enableLoadMore(Boolean enableLoadMore) {
        mEnableLoadMore = enableLoadMore;
    }

}
