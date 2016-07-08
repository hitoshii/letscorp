package org.wiky.letscorp.view;/** * Created by wiky on 7/3/16. */import android.animation.Animator;import android.content.Context;import android.support.v4.widget.NestedScrollView;import android.util.AttributeSet;import android.view.MotionEvent;import android.view.View;import android.view.ViewPropertyAnimator;import android.view.animation.DecelerateInterpolator;public class OverScrollView extends NestedScrollView {    private int mLastEventY;    private OverScrollListener mListener;    private ViewPropertyAnimator mAnimator;    private Animator.AnimatorListener mAnimatorListener = new Animator.AnimatorListener() {        @Override        public void onAnimationStart(Animator animation) {        }        @Override        public void onAnimationEnd(Animator animation) {            mAnimator = null;        }        @Override        public void onAnimationCancel(Animator animation) {            mAnimator = null;        }        @Override        public void onAnimationRepeat(Animator animation) {        }    };    public OverScrollView(Context context) {        super(context);    }    public OverScrollView(Context context, AttributeSet attrs) {        super(context, attrs);    }    public OverScrollView(Context context, AttributeSet attrs, int defStyleAttr) {        super(context, attrs, defStyleAttr);    }    @Override    public boolean onTouchEvent(MotionEvent event) {        if (mAnimator != null) {            mAnimator.cancel();        }        final int eventY = (int) event.getY();        switch (event.getAction()) {            case MotionEvent.ACTION_UP:                int yDistance = (int) getTranslationY();                if (yDistance != 0) {                    if (mListener == null || !mListener.onOverScroll(yDistance, true)) { //only do this if mListener returns false                        mAnimator = animate().translationY(0)                                .setDuration(600)                                .setListener(mAnimatorListener)                                .setInterpolator(new DecelerateInterpolator(6));                        mAnimator.start();                    }                }                break;            case MotionEvent.ACTION_DOWN:                mLastEventY = eventY;                break;            case MotionEvent.ACTION_MOVE:                if (getScrollY() == 0) {                    handleOverscroll(event, false);                } else {                    View view = getChildAt(getChildCount() - 1);                    if (view.getHeight() <= (getHeight() + getScrollY())) {                        handleOverscroll(event, true);                    }                }                break;        }        if ((int) (getTranslationY()) != 0) {            return true;        }        return super.onTouchEvent(event);    }    public void setOverScrollListener(OverScrollListener listener) {        this.mListener = listener;    }    private void handleOverscroll(MotionEvent ev, boolean isBottom) {        int pointerCount = ev.getHistorySize();        for (int p = 0; p < pointerCount; p++) {            int historicalY = (int) ev.getHistoricalY(p);            int yDistance = (historicalY - mLastEventY) / 6;            if ((isBottom && yDistance < 0) || (!isBottom && yDistance > 0)) {                setTranslationY(yDistance);                if (mListener != null) mListener.onOverScroll(yDistance, false);            }        }    }    public interface OverScrollListener {        boolean onOverScroll(int yDistance, boolean isReleased);    }}