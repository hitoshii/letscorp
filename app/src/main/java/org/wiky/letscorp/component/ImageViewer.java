package org.wiky.letscorp.component;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.BitmapTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.target.Target;

import org.wiky.letscorp.Application;
import org.wiky.letscorp.R;
import org.wiky.letscorp.api.Const;

/**
 * Created by wiky on 8/9/16.
 */
public class ImageViewer extends ImageView {
    private String mUrl = "";

    public ImageViewer(Context context) {
        super(context);
    }

    public ImageViewer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageViewer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ImageViewer(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
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

        Log.d("url", mUrl);
        GlideUrl glideUrl = new GlideUrl(mUrl, new LazyHeaders.Builder()
                .addHeader("User-Agent", Const.HTTP_USER_AGENT)
                .build());
        BitmapTypeRequest req = Glide.with(Application.getApplication()).load(glideUrl).asBitmap();
        if (loading) {
            req.placeholder(R.mipmap.ic_photo_black);
        }
        req.error(R.mipmap.ic_broken_image_black)
                .fitCenter()
                .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                .into(this);
    }

}
