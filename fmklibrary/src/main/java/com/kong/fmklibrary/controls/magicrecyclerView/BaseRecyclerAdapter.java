package com.kong.fmklibrary.controls.magicrecyclerView;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public abstract class BaseRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public final static int TYPE_NORMAL = 0;
    public final static int TYPE_HEADER = 1;
    public final static int TYPE_FOOTER = 2;
    public final static int TYPE_TAGS = 3;

    private ArrayList<BaseItem> mDatas = new ArrayList<>();
    private View mHeaderView;
    private View mFooterView;
    private OnItemClickListener mListener;

    void setOnItemClickListener(OnItemClickListener li) {
        mListener = li;
    }

    void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    void setFooterView(View footerView) {
        mFooterView = footerView;
        notifyItemInserted(getItemCount());
    }

    /**
     * 根据特定的 Item 传入数据可设置 Tag
     *
     * @param datas
     */
    public void setBaseDatas(ArrayList<BaseItem> datas) {
        mDatas.clear();
        if (datas != null) {
            mDatas.addAll(datas);
        }
        notifyDataSetChanged();
    }

    /**
     * 根据特定的 Item 传入数据可设置 Tag
     *
     * @param datas
     */
    public void addBaseDatas(ArrayList<BaseItem> datas) {
        if (datas != null) {
            mDatas.addAll(datas);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null) {
            if (mFooterView == null) {
                //headerView footerView 都为空时
                if (mDatas.get(position).getItemType() == TYPE_NORMAL) { //有头部视图时需要对位置进行纠正
                    return TYPE_NORMAL;
                } else if (mDatas.get(position).getItemType() == TYPE_TAGS) {
                    return TYPE_TAGS;
                }
            } else {
                //headerView 为空 footerView 不为空时
                if (position == mDatas.size()) {
                    return TYPE_FOOTER;
                } else {
                    if (mDatas.get(position).getItemType() == TYPE_NORMAL) { //有头部视图时需要对位置进行纠正
                        return TYPE_NORMAL;
                    } else if (mDatas.get(position).getItemType() == TYPE_TAGS) {
                        return TYPE_TAGS;
                    }
                }
            }
        } else {
            if (mFooterView == null) {
                //headerView 不为空 footerView 为空时
                if (position == 0) {
                    return TYPE_HEADER;
                } else {
                    if (mDatas.get(position - 1).getItemType() == TYPE_NORMAL) { //有头部视图时需要对位置进行纠正
                        return TYPE_NORMAL;
                    } else if (mDatas.get(position - 1).getItemType() == TYPE_TAGS) {
                        return TYPE_TAGS;
                    }
                }
            } else {
                //headerView 不为空 footerView 也不为空时
                if (position == 0) {
                    return TYPE_HEADER;
                } else if (position == mDatas.size() + 1) {
                    return TYPE_FOOTER;
                } else {
                    if (mDatas.get(position - 1).getItemType() == TYPE_NORMAL) { //有头部和底部视图时需要对位置进行纠正
                        return TYPE_NORMAL;
                    } else if (mDatas.get(position - 1).getItemType() == TYPE_TAGS) {
                        return TYPE_TAGS;
                    }
                }
            }
        }
        if (mHeaderView != null) { //包含头部控件时
            if (position == 0) {
                return TYPE_HEADER; //返回HEADER对应的数值
            } else {
                if (mDatas.get(position - 1).getItemType() == TYPE_NORMAL) { //有头部视图时需要对位置进行纠正
                    return TYPE_NORMAL;
                } else if (mDatas.get(position - 1).getItemType() == TYPE_TAGS) {
                    return TYPE_TAGS;
                } else {
                    return TYPE_FOOTER;
                }
            }
        } else { //不包含头部控件时
            if (mDatas.get(position).getItemType() == TYPE_NORMAL) { //有头部视图时需要对位置进行纠正
                return TYPE_NORMAL;
            } else if (mDatas.get(position).getItemType() == TYPE_TAGS) {
                return TYPE_TAGS;
            } else {
                return TYPE_FOOTER;
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER)
            return new Holder(mHeaderView);
        if (mFooterView != null && viewType == TYPE_FOOTER)
            return new Holder(mFooterView);
        return onCreate(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (getItemViewType(position) == TYPE_HEADER)
            return;
        if (getItemViewType(position) == TYPE_FOOTER)
            return;
        final int pos = getRealPosition(viewHolder);
        final BaseItem data = mDatas.get(pos);
        onBind(viewHolder, pos, data);
        if (data.getItemType() == TYPE_NORMAL) { //普通的item才可以点击
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onItemClick(pos, data, v);
                    }
                }
            });
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return (getItemViewType(position) == TYPE_HEADER ||
                            getItemViewType(position) == TYPE_FOOTER ||
                            getItemViewType(position) == TYPE_TAGS)
                            ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        if ((holder.getItemViewType() == TYPE_HEADER) ||
                (holder.getItemViewType() == TYPE_FOOTER) ||
                (holder.getItemViewType() == TYPE_TAGS)) {
            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
            if (lp != null && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
                StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
                p.setFullSpan(true);
            }
        }
    }

    /**
     * 获取真实的位置信息，如果添加了头部视图，数据源跟position的对应需要调整
     *
     * @param holder 绑定的holder
     * @return 显示数据的item的修正position
     */
    private int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }

    @Override
    public int getItemCount() {
        if (mHeaderView == null) {
            if (mFooterView == null) {
                //headerView footerView 都为空时
                return mDatas.size();
            } else {
                //headerView 为空 footerView 不为空时
                return mDatas.size() + 1;
            }
        } else {
            if (mFooterView == null) {
                //headerView 不为空 footerView 为空时
                return mDatas.size() + 1;
            } else {
                //headerView 不为空 footerView 也不为空时
                return mDatas.size() + 2;
            }
        }
    }

    public abstract RecyclerView.ViewHolder onCreate(ViewGroup parent, final int viewType);

    public abstract void onBind(RecyclerView.ViewHolder viewHolder, int RealPosition, BaseItem data);

    public class Holder extends RecyclerView.ViewHolder {
        public Holder(View itemView) {
            super(itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position, BaseItem data, View view);
    }


    String getTag(int position) {
        if (mHeaderView != null) {
            position = position - 1;
        }
        return (String) mDatas.get(position).getData();
    }
}