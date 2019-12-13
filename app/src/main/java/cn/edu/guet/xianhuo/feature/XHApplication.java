package cn.edu.guet.xianhuo.feature;

import android.app.Application;

import androidx.preference.PreferenceManager;

import com.squareup.leakcanary.LeakCanary;

import cn.edu.guet.xianhuo.R;
import cn.edu.guet.xianhuo.base.wrapper.ToastWrapper;

/**
 * App-Application
 */
public class XHApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // 此进程是LeakCanary用于内存堆分析的.
            // 不应该在此进程中做任何app初始化工作.
            return;
        }
        //初始化LeakCanary
        LeakCanary.install(this);
        //初始化Toast工具类
        ToastWrapper.init(this);
        //设置偏好设置
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
    }

}
