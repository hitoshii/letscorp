package org.wiky.letscorp.api;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created by wiky on 7/18/16.
 */
public class Request {
    private static final String USER_AGENT = "Mozilla/5.0 (X11; Fedora; Linux x86_64; rv:47.0) Gecko/20100101 Firefox/47.0";
    private static OkHttpClient mClient = new OkHttpClient().newBuilder().followRedirects(true).followSslRedirects(true).build();

    public static Call get(String url, Callback callback) {
        okhttp3.Request request = new okhttp3.Request.Builder()
                .addHeader("User-Agent", USER_AGENT)
                .url(url)
                .build();
        Call call = mClient.newCall(request);
        new RequestTask().execute(new TaskData(call, callback));
        return call;
    }

    public interface Callback {
        void onSuccess(Document doc);

        void onCancelled();

        void onError(Exception e);
    }

    private static class TaskData {
        public Call call;
        public Callback callback;
        public Response response;
        public Document doc;
        public Exception e;

        public TaskData(Call call, Callback callback) {
            this.call = call;
            this.callback = callback;
        }
    }

    private static class RequestTask extends AsyncTask<TaskData, Void, TaskData> {

        @Override
        protected TaskData doInBackground(TaskData... datas) {
            TaskData data = datas[0];
            Call call = data.call;
            try {
                data.response = call.execute();
                String body = data.response.body().string();
                data.doc = Jsoup.parse(body);
            } catch (IOException e) {
                e.printStackTrace();
                data.e = e;
            }
            return data;
        }

        @Override
        protected void onPostExecute(TaskData data) {
            Callback callback = data.callback;
            if (data.call.isCanceled()) {
                Log.d("request", "cancelled");
                callback.onCancelled();
            } else if (data.e == null && data.doc != null) {
                callback.onSuccess(data.doc);
            } else {
                callback.onError(data.e);
            }
        }
    }
}
