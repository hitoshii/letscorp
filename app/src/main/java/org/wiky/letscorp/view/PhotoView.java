package org.wiky.letscorp.view;

import android.content.Context;
import android.util.AttributeSet;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import org.wiky.letscorp.Application;
import org.wiky.letscorp.R;

import uk.co.senab.photoview.PhotoViewAttacher;

public class PhotoView extends uk.co.senab.photoview.PhotoView {

    private PhotoViewAttacher mAttacher;
    private String mUrl;

    public PhotoView(Context context, AttributeSet attr) {
        super(context, attr);
        initialize();
    }

    public PhotoView(Context context) {
        super(context);
        initialize();
    }

    private void initialize() {
        mAttacher = new PhotoViewAttacher(this);
        mAttacher.setScaleType(ScaleType.FIT_CENTER);
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        setUrl(url, false);
    }

    public void setUrl(String url, boolean loading) {
        mUrl = url;
        if (mUrl.isEmpty()) {
            return;
        }
        RequestCreator req = Picasso.with(Application.getApplication())
                .load(url);
        if (loading) {
            req.placeholder(R.mipmap.ic_photo);
        }
        req.error(R.mipmap.ic_photo)
                .into(this, new Callback() {
                    @Override
                    public void onSuccess() {
                        mAttacher.update();
                    }

                    @Override
                    public void onError() {
                    }
                });
    }

    @Override
    public void setZoomable(boolean zoomable) {
        mAttacher.setZoomable(zoomable);
    }
}