package com.example.fj.second.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fj.second.R;
import com.example.fj.second.fragment.ItemFragment.OnListFragmentInteractionListener;
import com.example.fj.second.model.DataBean;

import java.util.List;

public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<DataBean> mBeanList;
    private final boolean isLinearLayoutManager;
    private final OnListFragmentInteractionListener mListener;

    /**
     * 构造方法
     *
     * @param columnCount 列数，通过列数不同加载不同布局
     * @param listener    点击事件
     */
    public MyItemRecyclerViewAdapter(List<DataBean> beanList, int columnCount, OnListFragmentInteractionListener listener) {
        mBeanList = beanList;
        mListener = listener;
        isLinearLayoutManager = columnCount == 1;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 当ViewHolder创建时的回调
        // View view = View.inflate(parent.getContext(), isLinearLayoutManager ? R.layout.fragment_item_linear : R.layout.fragment_item, null);
        View view = LayoutInflater.from(parent.getContext())
                .inflate(isLinearLayoutManager ? R.layout.fragment_item_linear : R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // 当ViewHolder和数据绑定时的回调
        holder.setData(mBeanList.get(position));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBeanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView ivIcon;
        public final TextView tvName;
        public DataBean mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ivIcon = (ImageView) view.findViewById(R.id.item_list_iv_icon);
            tvName = (TextView) view.findViewById(R.id.item_list_tv_name);
        }

        /**
         * 设置数据
         */
        public void setData(DataBean item) {
            mItem = item;
            ivIcon.setImageResource(item.getIcon());
            tvName.setText(item.getName());
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tvName.getText() + "'";
        }
    }
}
