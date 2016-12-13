package app.yy.cn.jrdt.bean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/13 0013.
 */
public class ZuQiuTabBean {
    public ZuQiuTab result;

    public class ZuQiuTab{

        public SaiCheng views;
    }

    public class SaiCheng{
        public ArrayList<Sc1> saicheng1;
        public ArrayList<Sc2> saicheng2;
    }

    public class Sc1{
        public String c1;
        public String c2;
        public String c3;
        public String c4T1;
        public String c4T1URL;
        public String c4R;
        public String c4T2;
        public String c4T2URL;
        public String c51;
        public String c51Link;
        public String c52;
        public String c52Link;
        public String liveid;

    }
    public class Sc2{
        public String c1;
        public String c2;
        public String c3;
        public String c4T1;
        public String c4T1URL;
        public String c4R;
        public String c4T2;
        public String c4T2URL;
        public String c52;
        public String c52Link;
        public String liveid;

    }

}
