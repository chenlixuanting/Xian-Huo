package cn.edu.guet.xianhuo.network.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import cn.edu.guet.xianhuo.network.core.ApiInterface;
import cn.edu.guet.xianhuo.network.core.ApiPath;
import cn.edu.guet.xianhuo.network.core.RequestParam;
import cn.edu.guet.xianhuo.network.core.ResponseEntity;
import cn.edu.guet.xianhuo.network.entity.User;

/**
 * 获取用户信息
 */
public class ApiUserInfo implements ApiInterface {

    @NonNull
    @Override
    public String getPath() {
        return ApiPath.USER_INFO;
    }

    @Nullable
    @Override
    public RequestParam getRequestParam() {
        return new Req();
    }

    @NonNull
    @Override
    public Class<? extends ResponseEntity> getResponseType() {
        return Rsp.class;
    }

    public static class Req extends RequestParam {
        @Override
        protected int sessionUsage() {
            return SESSION_MANDATORY;
        }
    }

    public static class Rsp extends ResponseEntity {
        @SerializedName("data")
        private User mUser;

        public User getUser() {
            return mUser;
        }
    }

}
