package org.wiky.letscorp.list;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import org.wiky.letscorp.data.model.Comment;
import org.wiky.letscorp.list.adapter.CommentAdapter;
import org.wiky.letscorp.list.anim.CommentAnimator;

import java.util.ArrayList;
import java.util.List;

import me.everything.android.ui.overscroll.VerticalOverScrollBounceEffectDecorator;
import me.everything.android.ui.overscroll.adapters.RecyclerViewOverScrollDecorAdapter;

/*
 * 评论列表
 */
public class CommentListView extends RecyclerView {
    private CommentAdapter mAdapter;

    public CommentListView(Context context) {
        super(context);
        initialize(context);
    }

    public CommentListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    public CommentListView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initialize(context);
    }

    private void initialize(Context context) {
        if (isInEditMode()) {
            return;
        }
        setLayoutManager(new LinearLayoutManager(context));
        mAdapter = new CommentAdapter(new ArrayList<Comment>());
        setAdapter(mAdapter);
        setItemAnimator(new CommentAnimator());
        addItemDecoration(new CardItemDecoration(8));

        new VerticalOverScrollBounceEffectDecorator(new RecyclerViewOverScrollDecorAdapter(this));
    }

    public void setOnItemLongClickListener(CommentAdapter.OnItemLongClickListener listener) {
        mAdapter.setOnItemLongClickListener(listener);
    }

    public void setComments(List<Comment> comments) {
        mAdapter.setComments(comments);
    }

}
