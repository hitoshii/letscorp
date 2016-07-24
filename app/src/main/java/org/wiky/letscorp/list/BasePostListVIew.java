package org.wiky.letscorp.list;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import org.wiky.letscorp.adapter.PostListAdapter;
import org.wiky.letscorp.anim.PostItemAnimator;
import org.wiky.letscorp.api.Const;


public abstract class BasePostListVIew extends RecyclerView {
    protected final int mPageCount = 15;
    protected PostListAdapter mAdapter;
    protected LinearLayoutManager mLayoutManager;
    protected int mCategory = Const.LETSCORP_CATEGORY_ALL;
    protected int mPage;
    protected boolean mLocal;
    protected boolean mReseting;
    /* 滚动事件监听 */
    private OnScrollListener mOnScrollListener = new OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (mReseting) {
                return;
            }

            int totalItemCount = mLayoutManager.getItemCount();
            int lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
            int visibleThreshold = mLayoutManager.findLastVisibleItemPosition() - mLayoutManager.findFirstVisibleItemPosition();

            if (!mAdapter.isLoading() && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                loadMore();
            }
        }
    };
    private OnRefreshListener mOnRefreshListener = null;

    public BasePostListVIew(Context context) {
        super(context);
        initialize(context);
    }

    public BasePostListVIew(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    public BasePostListVIew(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initialize(context);
    }

    private void initialize(Context context) {
        mAdapter = new PostListAdapter();
        mLayoutManager = new LinearLayoutManager(context);
        mPage = 1;
        mReseting = false;

        setAdapter(mAdapter);
        setLayoutManager(mLayoutManager);
        setItemAnimator(new PostItemAnimator());
        addItemDecoration(new CardItemDecoration(10));

        addOnScrollListener(mOnScrollListener);
    }

    public void setOnItemClickListener(PostListAdapter.OnItemClickListener listener) {
        mAdapter.setOnItemClickListener(listener);
    }

    public void setItemReadn(String href) {
        mAdapter.setItemReadn(href);
    }

    public void setCategory(int category) {
        mCategory = category;
    }

    public abstract void loadMore();

    @Override
    public boolean isInEditMode() {
        return true;
    }

    protected void onRefresh(boolean r) {
        if (mOnRefreshListener != null) {
            mOnRefreshListener.onRefresh(r);
        }
    }

    public void setOnRefreshListener(OnRefreshListener listener) {
        mOnRefreshListener = listener;
    }

    public interface OnRefreshListener {
        void onRefresh(boolean r);
    }

}
