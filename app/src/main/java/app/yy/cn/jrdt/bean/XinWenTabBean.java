package app.yy.cn.jrdt.bean;

import java.util.ArrayList;

/**
 * 页签详情数据对象
 * Created by Administrator on 2016/11/28 0028.
 */
public class XinWenTabBean {
    public NewsTab result;

    public class NewsTab{
        public ArrayList<NewsData> data;
    }

    public class NewsData{
        public String author_name;
        public String date;
        public String title;
        public String thumbnail_pic_s;
        public String thumbnail_pic_s03;
        public String url;
    }
}
