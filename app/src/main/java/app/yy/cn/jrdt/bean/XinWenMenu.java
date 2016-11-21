package app.yy.cn.jrdt.bean;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/17 0017.
 */
public class XinWenMenu {
    public String reason;

    public int retcode;
    public ArrayList<Integer> extend;
    public ArrayList<XinWenMenuData> data;

    @Override
    public String toString() {
        return "XinWenMenu{" +
                "data=" + data +
                '}';
    }

    //侧边栏菜单对象
    public class XinWenMenuData {
        public int id;
        public String title;
        public int type;

        public ArrayList<XinWenTabData> children;

        @Override
        public String toString() {
            return "XinWenMenuData{" +
                    "children=" + children +
                    ", title='" + title + '\'' +
                    '}';
        }
    }

    //页签对象
    public class XinWenTabData {
        public int id;
        public String title;
        public int type;
        public String url;

        @Override
        public String toString() {
            return "XinWenTabData{" +
                    "title='" + title + '\'' +
                    '}';
        }
    }

}
