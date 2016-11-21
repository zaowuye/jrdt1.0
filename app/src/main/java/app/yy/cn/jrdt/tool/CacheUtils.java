package app.yy.cn.jrdt.tool;

import android.content.Context;

/**
 * 网络缓存的工具类
 * Created by Administrator on 2016/11/20 0020.
 */
public class CacheUtils {

    /**
     * 缓存原理
     * 以url为key,以json为value,保存在本地
     * @param url
     * @param json
     */

    /**
     * 写入缓存
     * @param url
     * @param json
     * @param ctx
     */
    public static void setCache(String url, String json, Context ctx){
        PrefUtils.setString(ctx,url,json);
    }

    /**
     * 获取缓存
     * @param url
     * @param ctx
     * @return
     */
    public static String getCache(String url, Context ctx){
        return PrefUtils.getString(ctx,url,null);
    }
}
