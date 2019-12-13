package cn.edu.guet.xianhuo.network.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import cn.edu.guet.xianhuo.network.core.ApiInterface;
import cn.edu.guet.xianhuo.network.core.ApiPath;
import cn.edu.guet.xianhuo.network.core.RequestParam;
import cn.edu.guet.xianhuo.network.core.ResponseEntity;
import cn.edu.guet.xianhuo.network.entity.Address;

/**
 * 收货地址列表接口.
 */
public class ApiAddressList implements ApiInterface {

    @NonNull
    @Override
    public String getPath() {
        return ApiPath.ADDRESS_LIST;
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
        private List<Address> mData;

        public List<Address> getData() {
            return mData;
        }
    }

}
