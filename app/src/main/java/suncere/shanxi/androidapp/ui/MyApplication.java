package suncere.shanxi.androidapp.ui;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.lzy.okhttputils.OkHttpUtils;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Hjo on 2017/4/6.
 */
public class MyApplication extends Application {
    private static MyApplication myApplication;

    @Override
    public void onCreate() {
        myApplication=this;
        super.onCreate();

        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        // 必须调用初始化
        OkHttpUtils.init(this);
        // 以下都不是必须的，根据需要自行选择
        OkHttpUtils.getInstance()
                .debug("OkHttpUtils")                                              // 是否打开调试
                .setConnectTimeout(OkHttpUtils.DEFAULT_MILLISECONDS)               // 全局的连接超时时间
                .setReadTimeOut(OkHttpUtils.DEFAULT_MILLISECONDS)                  // 全局的读取超时时间
                .setWriteTimeOut(OkHttpUtils.DEFAULT_MILLISECONDS);          // 全局的写入超时时间
        //.setCookieStore(new MemoryCookieStore())                           // cookie使用内存缓存（app退出后，cookie消失）
        //.setCookieStore(new PersistentCookieStore())                       // cookie持久化存储，如果cookie不过期，则一直有效
    }



    /**
     * 获取Application的context
     * @return
     */
    public static Context getMyApplicationContext(){
        return myApplication.getApplicationContext();

    }


    /**
     * 获取当前APP版本号
     * @return
     */
    public static int getMyApplicationVersion(){
        try {
            PackageInfo info=getMyApplicationContext().getPackageManager().getPackageInfo(getMyApplicationContext().getPackageName(),0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return 1;
    }

    /**
     * 获取当前APP版本名称
     * @return
     */
    public static String  getMyApplicationVersionName(){
        try {
            PackageInfo info=getMyApplicationContext().getPackageManager().getPackageInfo(getMyApplicationContext().getPackageName(),0);
            return info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return "1.0.0";
    }
}
