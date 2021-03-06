package org.wiky.letscorp.api;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wiky on 6/11/16.
 */
public final class Const {
    public final static String LETSCORP_HOST = "https://m.letscorp.net";
    public final static String HTTP_USER_AGENT = "Mozilla/5.0 (X11; Fedora; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36";
    public final static String HTTP_ACCEPT = "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8";

    public final static String URL_POST_COMMENT = LETSCORP_HOST + "/lynn/wp-comments-post.php";

    public final static int LETSCORP_CATEGORY_ALL = 0;
    public final static int LETSCORP_CATEGORY_ECONOMICS = 1;
    public final static int LETSCORP_CATEGORY_NEWS = 2;
    public final static int LETSCORP_CATEGORY_VIEW = 3;
    public final static int LETSCORP_CATEGORY_POLITICS = 4;
    public final static int LETSCORP_CATEGORY_GALLERY = 5;
    public final static int LETSCORP_CATEGORY_RUMOR = 6;
    public final static int LETSCORP_CATEGORY_TECH = 7;
    public final static int LETSCORP_CATEGORY_HISTORY = 8;
    public final static int LETSCORP_CATEGORY_SEARCH = 9;

    private static Map<Integer, String> mCategoryURL = new HashMap<>();

    static {
        mCategoryURL.put(LETSCORP_CATEGORY_ALL, LETSCORP_HOST + "/page/");
        mCategoryURL.put(LETSCORP_CATEGORY_ECONOMICS, getCategoryURL(LETSCORP_CATEGORY_ECONOMICS));
        mCategoryURL.put(LETSCORP_CATEGORY_NEWS, getCategoryURL(LETSCORP_CATEGORY_NEWS));
        mCategoryURL.put(LETSCORP_CATEGORY_VIEW, getCategoryURL(LETSCORP_CATEGORY_VIEW));
        mCategoryURL.put(LETSCORP_CATEGORY_POLITICS, getCategoryURL(LETSCORP_CATEGORY_POLITICS));
        mCategoryURL.put(LETSCORP_CATEGORY_GALLERY, getCategoryURL(LETSCORP_CATEGORY_GALLERY));
        mCategoryURL.put(LETSCORP_CATEGORY_RUMOR, getCategoryURL(LETSCORP_CATEGORY_RUMOR));
        mCategoryURL.put(LETSCORP_CATEGORY_TECH, getCategoryURL(LETSCORP_CATEGORY_TECH));
        mCategoryURL.put(LETSCORP_CATEGORY_HISTORY, getCategoryURL(LETSCORP_CATEGORY_HISTORY));
    }

    public static String getCategoryName(int category) {
        switch (category) {
            case LETSCORP_CATEGORY_ECONOMICS:
                return "economics";
            case LETSCORP_CATEGORY_NEWS:
                return "news";
            case LETSCORP_CATEGORY_VIEW:
                return "view";
            case LETSCORP_CATEGORY_POLITICS:
                return "politics";
            case LETSCORP_CATEGORY_GALLERY:
                return "gallery";
            case LETSCORP_CATEGORY_RUMOR:
                return "rumor";
            case LETSCORP_CATEGORY_TECH:
                return "tech";
            case LETSCORP_CATEGORY_HISTORY:
                return "history";
        }
        return "";
    }

    private static String getCategoryURL(int category) {
        String name = getCategoryName(category);
        return LETSCORP_HOST + "/archives/category/" + name + "/page/";
    }

    public static String getPostListUrl(int cagegory, int page) {
        return mCategoryURL.get(cagegory) + page;
    }

    public static String getSearchUrl(String query, int page) {
        return LETSCORP_HOST + "/search/" + query + "/page/" + page;
    }

}
