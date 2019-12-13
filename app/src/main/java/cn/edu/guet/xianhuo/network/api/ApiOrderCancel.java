package cn.edu.guet.xianhuo.network.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import cn.edu.guet.xianhuo.network.core.ApiInterface;
import cn.edu.guet.xianhuo.network.core.ApiPath;
import cn.edu.guet.xianhuo.network.core.RequestParam;
import cn.edu.guet.xianhuo.network.core.ResponseEntity;

/**
 * 取消订单
 */
public class ApiOrderCancel implements ApiInterface {

    private final Req mReq;

    public ApiOrderCancel(int orderId) {
        mReq = new Req();
        mReq.mOrderId = orderId;
    }

    @NonNull
    @Override public String getPath() {
        return ApiPath.ORDER_CANCEL;
    }

    @Nullable
    @Override public RequestParam getRequestParam() {
        return mReq;
    }

    @NonNull @Override public Class<? extends ResponseEntity> getResponseType() {
        return Rsp.class;
    }

    public static class Req extends RequestParam {
        @SerializedName("order_id") private int mOrderId;
        @Override protected int sessionUsage() {
            return SESSION_MANDATORY;
        }
    }

    public static class Rsp extends ResponseEntity {
    }

}
