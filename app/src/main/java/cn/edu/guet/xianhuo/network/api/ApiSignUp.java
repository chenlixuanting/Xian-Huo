package cn.edu.guet.xianhuo.network.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import cn.edu.guet.xianhuo.network.core.ApiInterface;
import cn.edu.guet.xianhuo.network.core.ApiPath;
import cn.edu.guet.xianhuo.network.core.RequestParam;
import cn.edu.guet.xianhuo.network.core.ResponseEntity;
import cn.edu.guet.xianhuo.network.entity.Session;
import cn.edu.guet.xianhuo.network.entity.User;

/**
 * 服务器接口: 用户注册.
 */
public class ApiSignUp implements ApiInterface {

    private ApiSignUp.Req mReq;

    public ApiSignUp(@NonNull String name, @NonNull String email, @NonNull String password) {
        mReq = new Req();
        mReq.mName = name;
        mReq.mPassword = password;
        mReq.mEmail = email;
    }

    @NonNull
    @Override
    public String getPath() {
        return ApiPath.USER_SIGNUP;
    }

    @Nullable
    @Override
    public RequestParam getRequestParam() {
        return mReq;
    }

    @NonNull
    @Override
    public Class<? extends ResponseEntity> getResponseType() {
        return Rsp.class;
    }

    public static class Req extends RequestParam {
        @SerializedName("name")
        private String mName;
        @SerializedName("email")
        private String mEmail;
        @SerializedName("password")
        private String mPassword;

        @Override
        protected int sessionUsage() {
            return SESSION_NO_NEED;
        }
    }

    public static class Rsp extends ResponseEntity {

        @SerializedName("data")
        Data mData;

        public Data getData() {
            return mData;
        }

        public static class Data {
            @SerializedName("session")
            private Session mSession;
            @SerializedName("user")
            private User mUser;

            public Session getSession() {
                return mSession;
            }

            public User getUser() {
                return mUser;
            }
        }
    }

}
