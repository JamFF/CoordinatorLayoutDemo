package com.example.fj.second.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.fj.second.R;
import com.example.fj.second.adapter.MyItemRecyclerViewAdapter;
import com.example.fj.second.model.DATAS;
import com.example.fj.second.model.DataBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：RecyclerView的Fragment
 * <p>
 * 对应的Activity必须实现{@link OnListFragmentInteractionListener}这个接口
 * 作者：JamFF
 * 创建时间：2016/12/18 11:22
 */
public class ItemFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String ARG_COLUMN_COUNT = "column-count";

    private static final String ARG_ORIENTATION = "orientation";

    private static final String ARG_REVERSE_LAYOUT = "reverse_layout";

    private static final String ARG_STAGGERED = "staggered";

    // 列数
    private int mColumnCount = 1;

    // 滑动方向默认垂直
    private int mOrientation = LinearLayout.VERTICAL;

    // 展示方向默认升序
    private boolean mReverseLayout = false;

    // 是否是瀑布流
    private boolean mStaggered = false;

    private OnListFragmentInteractionListener mListener;

    // 下拉刷新的控件
    private SwipeRefreshLayout mRefreshLayout;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    mRefreshLayout.setRefreshing(false);
                    break;
            }
        }
    };

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemFragment() {
    }

    /**
     * 传入RecyclerView的LayoutManager参数
     *
     * @param columnCount   列数
     * @param orientation   LinearLayout.VERTICAL垂直滑动 LinearLayout.HORIZONTAL水平滑动
     * @param reverseLayout false:升序 true:降序
     * @param staggered     false:非瀑布流 true:瀑布流
     */
    public static ItemFragment newInstance(int columnCount, int orientation, boolean reverseLayout, boolean staggered) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        args.putInt(ARG_ORIENTATION, orientation);
        args.putBoolean(ARG_REVERSE_LAYOUT, reverseLayout);
        args.putBoolean(ARG_STAGGERED, staggered);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            mOrientation = getArguments().getInt(ARG_ORIENTATION);
            mReverseLayout = getArguments().getBoolean(ARG_REVERSE_LAYOUT, false);
            mStaggered = getArguments().getBoolean(ARG_STAGGERED, false);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        View list = view.findViewById(R.id.list);

        if (view instanceof SwipeRefreshLayout) {
            mRefreshLayout = (SwipeRefreshLayout) view;
            mRefreshLayout.setOnRefreshListener(this);
            mRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.BLACK);
        }

        if (list instanceof RecyclerView) {
            // Set RecyclerView adapter
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) list;

            List<DataBean> beanList = new ArrayList<>();

            if (mOrientation != LinearLayout.VERTICAL && mOrientation != LinearLayout.HORIZONTAL) {
                mOrientation = LinearLayout.VERTICAL;
            }

            if (mColumnCount <= 1) {
                mColumnCount = 1;
                recyclerView.setLayoutManager(new LinearLayoutManager(context, mOrientation, mReverseLayout));

                for (int i = 0; i < DATAS.ICONS.length; i++) {
                    DataBean bean = new DataBean();
                    bean.setIcon(DATAS.ICONS[i]);
                    bean.setName("图片-" + i);
                    beanList.add(bean);
                }

            } else {
                if (mStaggered) {
                    StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(mColumnCount, mOrientation);
                    layoutManager.setReverseLayout(mReverseLayout);
                    recyclerView.setLayoutManager(layoutManager);

                    for (int i = 0; i < DATAS.PICS.length; i++) {
                        DataBean bean = new DataBean();
                        bean.setIcon(DATAS.PICS[i]);
                        bean.setName("图片-" + i);
                        beanList.add(bean);
                    }
                } else {
                    recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount, mOrientation, mReverseLayout));

                    for (int i = 0; i < DATAS.ICONS.length; i++) {
                        DataBean bean = new DataBean();
                        bean.setIcon(DATAS.ICONS[i]);
                        bean.setName("图片-" + i);
                        beanList.add(bean);
                    }
                }
            }

            recyclerView.setAdapter(new MyItemRecyclerViewAdapter(beanList, mColumnCount, mListener));
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        mListener = null;
        mHandler.removeCallbacksAndMessages(null);
        super.onDetach();
    }

    @Override
    public void onRefresh() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(5000);
                mHandler.sendEmptyMessage(0);
            }
        }).start();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DataBean item);
    }
}
