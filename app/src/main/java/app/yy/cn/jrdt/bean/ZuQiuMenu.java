package app.yy.cn.jrdt.bean;

import android.content.Intent;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/7 0007.
 */
public class ZuQiuMenu {
    public int retcode;

    public ArrayList<ZuQiuMenuData> data;


    public class ZuQiuMenuData{

    public ArrayList<ZuQiuTabData> children;

        @Override
        public String toString() {
            return "ZuQiuMenuData{" +
                    "children=" + children +
                    '}';
        }
    }


    //页签对象
    public class ZuQiuTabData{
        public int id;
        public String title;
        public int type;
        public String url;

        @Override
        public String toString() {
            return "ZuQiuTabData{" +
                    "title='" + title + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ZuQiuMenu{" +
                "data=" + data +
                '}';
    }
}
