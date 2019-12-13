package cn.edu.guet.xianhuo.network.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import cn.edu.guet.xianhuo.network.core.ApiInterface;
import cn.edu.guet.xianhuo.network.core.ApiPath;
import cn.edu.guet.xianhuo.network.core.RequestParam;
import cn.edu.guet.xianhuo.network.core.ResponseEntity;
import cn.edu.guet.xianhuo.network.entity.Address;

/**
 * 获取地址信息
 */
public class ApiAddressInfo implements ApiInterface {

    private Req mReq;

    public ApiAddressInfo(int addressId) {
        mReq = new Req();
        mReq.mId = addressId;
    }

    @NonNull
    @Override
    public String getPath() {
        return ApiPath.ADDRESS_INFO;
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
        @SerializedName("address_id")
        private int mId;

        @Override
        protected int sessionUsage() {
            return SESSION_MANDATORY;
        }
    }

    public static class Rsp extends ResponseEntity {
        @SerializedName("data")
        private Address mData;

        public Address getData() {
            return mData;
        }
    }

}
