package org.huihui.gank;

import java.util.List;

/**
 * Created by Administrator on 2017/3/20.
 */

public class MeiZiReponse {
    public boolean error;
    public List<ResultsBean> results;

    public static class ResultsBean {
        public String _id;
        public String createdAt;
        public String desc;
        public String publishedAt;
        public String source;
        public String type;
        public String url;
        public boolean used;
        public String who;
    }
}
