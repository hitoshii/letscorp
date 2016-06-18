package org.wiky.letscorp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;

import org.wiky.letscorp.R;
import org.wiky.letscorp.adapter.PostListAdapter;
import org.wiky.letscorp.api.API;
import org.wiky.letscorp.data.model.PostItem;
import org.wiky.letscorp.util.Util;
import org.wiky.letscorp.view.PostListView;

public class MainActivity extends BaseDrawerActivity implements SwipeRefreshLayout.OnRefreshListener {

    private PostListView mPostListView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private PostListAdapter.OnItemClickListener mOnItemClickListener = new PostListAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(final PostItem data) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(MainActivity.this, PostActivity.class);
                    intent.putExtra("data", data);
                    startActivity(intent);
                }
            }, 150);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPostListView = (PostListView) findViewById(R.id.post_list);
        mPostListView.setOnItemClickListener(mOnItemClickListener);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        startAnimation();
    }

    private void startAnimation() {
        float actionbarSize = Util.dp2px(56);
        mToolBar.setTranslationY(-actionbarSize);
        mToolBar.animate().translationY(0)
                .setDuration(300)
                .setStartDelay(300);
    }

    @Override
    public void onRefresh() {
        mPostListView.resetPage(new API.HttpFinalHandler() {
            @Override
            public void onFinally() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

}
