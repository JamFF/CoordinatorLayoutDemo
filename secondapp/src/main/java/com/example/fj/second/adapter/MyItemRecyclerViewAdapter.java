package com.example.fj.second.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fj.second.R;
import com.example.fj.second.fragment.OnItemClickListener;
import com.example.fj.second.model.DataBean;

import java.util.List;

public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> implements View.OnClickListener, View.OnLongClickListener {

    private final List<DataBean> mBeanList;
    private final boolean isLinearLayoutManager;
    private OnItemClickListener mListener;

    /**
     * 构造方法
     *
     * @param columnCount 列数，通过列数不同加载不同布局
     */
    public MyItemRecyclerViewAdapter(List<DataBean> beanList, int columnCount) {
        mBeanList = beanList;
        isLinearLayoutManager = columnCount == 1;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 当ViewHolder创建时的回调
        // View view = View.inflate(parent.getContext(), isLinearLayoutManager ? R.layout.fragment_item_linear : R.layout.fragment_item, null);
        // 上面的方法最后调用的也是LayoutInflater的inflate方法
        View view = LayoutInflater.from(parent.getContext())
                .inflate(isLinearLayoutManager ? R.layout.fragment_item_recycler_linear : R.layout.fragment_item_recycler_grid, parent, false);
        // 这里设置点击事件性能更好
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        // 当ViewHolder和数据绑定时的回调
        holder.setData(mBeanList.get(position));

        // 这里添加点击事件消耗性能，使用下面的方式
        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onItemClick(holder.getAdapterPosition());
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mListener.onItemLongClick(holder.getAdapterPosition());
                return false;
            }
        });*/

        holder.itemView.setTag(holder.getAdapterPosition());
    }

    @Override
    public int getItemCount() {
        return mBeanList.size();
    }

    @Override
    public void onClick(View v) {
        if (null != mListener) {
            mListener.onItemClick((int) v.getTag());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (null != mListener) {
            mListener.onItemLongClick((int) v.getTag());
        }
        return false;
    }

    public void removeDate(int position) {
        mBeanList.remove(position);
        notifyItemRemoved(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final ImageView ivIcon;
        public final TextView tvName;
        public DataBean mItem;

        public ViewHolder(View view) {
            super(view);
            ivIcon = view.findViewById(R.id.item_list_iv_icon);
            tvName = view.findViewById(R.id.item_list_tv_name);
        }

        /**
         * 设置数据
         */
        public void setData(DataBean item) {
            mItem = item;
            ivIcon.setImageResource(item.getIcon());
            tvName.setText(item.getName());
        }

        @NonNull
        @Override
        public String toString() {
            return super.toString() + " '" + tvName.getText() + "'";
        }
    }
}
