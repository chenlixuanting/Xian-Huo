package cn.edu.guet.xianhuo.network.core;


import androidx.annotation.IntDef;

import com.google.gson.annotations.SerializedName;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import cn.edu.guet.xianhuo.network.UserManager;
import cn.edu.guet.xianhuo.network.entity.Session;

/**
 * 请求参数的基类.
 */
public abstract class RequestParam {

    protected static final int SESSION_NO_NEED = 0;
    protected static final int SESSION_OPTIONAL = 1;
    public static final int SESSION_MANDATORY = 2;

    @IntDef({SESSION_NO_NEED, SESSION_OPTIONAL, SESSION_MANDATORY})
    @Retention(RetentionPolicy.SOURCE)
    @interface SessionUsage {
    }

    @SerializedName("session") private Session mSession;

    public RequestParam() {
        int usage = sessionUsage();
        if (usage == SESSION_NO_NEED) return;
        Session session = UserManager.getInstance().getSession();
        if (usage == SESSION_MANDATORY && session == null) {
            throw new IllegalStateException("Session is mandatory.");
        }
        mSession = session;
    }

    @SessionUsage protected abstract int sessionUsage();

}
